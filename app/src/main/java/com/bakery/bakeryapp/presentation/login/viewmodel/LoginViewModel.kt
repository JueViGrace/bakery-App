package com.bakery.bakeryapp.presentation.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.rules.Validator
import com.bakery.bakeryapp.presentation.login.events.LoginUIEvent
import com.bakery.bakeryapp.presentation.login.state.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    private val _state = MutableStateFlow(LoginUIState())
    val state: StateFlow<LoginUIState> = _state.asStateFlow()
    /*.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        LoginUIState()
    )*/

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                _state.update { state ->
                    return@update state.copy(email = event.email)
                }
            }

            is LoginUIEvent.PasswordChanged -> {
                _state.update { state ->
                    return@update state.copy(password = event.password)
                }
            }

            is LoginUIEvent.LogingButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun login() {
        validateLoginUIDataWithRules()

        val login = Login(
            email = _state.value.email,
            password = _state.value.password
        )

        Log.d(TAG, "_state before login: ${_state.value}")

        sendLogin(login)
    }

    private fun sendLogin(login: Login) {
        viewModelScope.launch {
            repository.login(login).collect { value ->
                when (value) {
                    is Resource.Error -> {
                        _state.update { state ->
                            return@update state.copy(
                                loginInProgress = false,
                                loginError = true,
                                loggedIn = false,
                                loginMessage = value.message
                            )
                        }
                        Log.d(TAG, "_state after login, error: ${_state.value}")
                    }

                    is Resource.Loading -> {
                        _state.update { state ->
                            return@update state.copy(loginInProgress = true, loggedIn = false)
                        }
                    }

                    is Resource.Success -> {
                        if (value.data != null) {
                            repository.saveUser(listOf(value.data.user))
                            repository.deleteToken()
                            repository.upsertToken(value.data.accessToken)
                            _state.update { state ->
                                return@update state.copy(
                                    loginError = false,
                                    loggedIn = true,
                                    loginInProgress = false
                                )
                            }
                            Log.d(TAG, "_state after login, success: ${_state.value}")
                        }
                    }
                }
            }
        }
    }

    private fun reset() {
        _state.update { state ->
            return@update state.copy(
                email = "",
                password = "",
                loginMessage = "",
                emailError = false,
                passwordError = false,
                loginError = false,
                allValidationsPassed = false,
                loginInProgress = false,
                loggedIn = false
            )
        }
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(email = _state.value.email)

        val passwordResult = Validator.validatePassword(password = _state.value.password)

        _state.update { state ->
            return@update state.copy(
                emailError = emailResult.status,
                passwordError = passwordResult.status,
                allValidationsPassed = emailResult.status && passwordResult.status
            )
        }
        Log.d(TAG, "_state validation: ${_state.value}")
    }
}
