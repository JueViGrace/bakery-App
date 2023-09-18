package com.bakery.bakeryapp.ui.viewmodel.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.rules.Validator
import com.bakery.bakeryapp.ui.states.login.LoginUIState
import com.bakery.bakeryapp.ui.uievents.login.LoginUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    val state = mutableStateOf(LoginUIState())

    val allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                state.value = state.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                state.value = state.value.copy(
                    password = event.password
                )
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
            email = state.value.email,
            password = state.value.password
        )

        loginApi(login)
    }

    private fun loginApi(login: Login) {
        viewModelScope.launch {
            val result = async {
                repository.login(login)
            }.await()

            result.collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            state.value = state.value.copy(
                                accessToken = it.data.access_token,
                                userId = it.data.user._id
                            )
                            repository.saveUser(listOf(it.data.user))
                            AppRouter.navigateTo(Screen.LoadingScreen)
                            loginInProgress.value = false

                            reset()
                        } else {
                            state.value.loginError = true
                        }
                    }

                    is Resource.Error -> {
                        loginInProgress.value = false
                        state.value.loginError = true
                        state.value = state.value.copy(
                            loginMessage = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loginInProgress.value = true
                    }
                }
            }
        }
    }

    private fun reset() {
        state.value = state.value.copy(
            email = "",
            password = "",
            accessToken = "",
            loginMessage = "",
            emailError = false,
            passwordError = false,
            loginError = false
        )
        allValidationsPassed.value = false
        loginInProgress.value = false
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(email = state.value.email)

        val passwordResult = Validator.validatePassword(password = state.value.password)

        state.value = state.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status
    }
}
