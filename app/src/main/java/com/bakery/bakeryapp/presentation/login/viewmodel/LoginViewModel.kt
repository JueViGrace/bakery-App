package com.bakery.bakeryapp.presentation.login.viewmodel

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

    // val state: MutableState<LoginUIState> = mutableStateOf(LoginUIState())

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
                _state.update {
                    return@update it.copy(email = event.email)
                }
            }

            is LoginUIEvent.PasswordChanged -> {
                _state.update {
                    return@update it.copy(password = event.password)
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

        loginApi(login)
    }

    private fun loginApi(login: Login) {
        viewModelScope.launch {
            repository.login(login).collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.saveUser(listOf(it.data.user))
                            repository.upsertToken(it.data.accessToken)
                            _state.update { state ->
                                return@update state.copy(
                                    accessToken = it.data.accessToken,
                                    userId = it.data.user._id,
                                    loggedIn = true,
                                    loginInProgress = false
                                )
                            }
                            reset()
                        }
                    }

                    is Resource.Error -> {
                        _state.update { state ->
                            return@update state.copy(
                                loginInProgress = false,
                                loginError = true,
                                loggedIn = false,
                                loginMessage = it.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.update { state ->
                            return@update state.copy(loginInProgress = true, loggedIn = false)
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
                accessToken = "",
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
    }
}
