package com.bakery.bakeryapp.presentation.auth.signup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.domain.rules.Validator
import com.bakery.bakeryapp.presentation.auth.signup.events.SignUpUIEvent
import com.bakery.bakeryapp.presentation.auth.signup.state.SignUpUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName

    private val _state = MutableStateFlow(SignUpUIState())
    val state = _state.asStateFlow()

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        firstName = event.firstName
                    )
                }
                printState()
            }

            is SignUpUIEvent.LastNameChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        lastName = event.lastName,
                        fullName = "${state.firstName} ${event.lastName}"
                    )
                }
                printState()
            }

            is SignUpUIEvent.EmailChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        email = event.email
                    )
                }
                printState()
            }

            is SignUpUIEvent.PasswordChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        password = event.password
                    )
                }
                printState()
            }

            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                _state.update { state ->
                    return@update state.copy(
                        privacyPolicyAccepted = event.status
                    )
                }
                printState()
            }

            is SignUpUIEvent.BirthDayChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        birthDay = event.birthDay
                    )
                }
                printState()
            }

            is SignUpUIEvent.PhoneChanged -> {
                _state.update { state ->
                    return@update state.copy(
                        phone = event.phone
                    )
                }
                printState()
            }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        validateDataWithRules()

        val register = Register(
            birthDate = state.value.birthDay,
            createdAt = state.value.createdAt,
            email = state.value.email,
            fullName = state.value.fullName,
            lastName = state.value.lastName,
            name = state.value.firstName,
            password = state.value.password,
            phone = state.value.phone,
            role = state.value.role
        )
        createUserInCloud(register)
    }

    private fun createUserInCloud(register: Register) {
        viewModelScope.launch {
            repository.register(register).collect { value ->
                when (value) {
                    is Resource.Error -> {
                        _state.update { state ->
                            return@update state.copy(
                                signUpInProgress = false,
                                signUpError = true,
                                signedUp = false,
                                signUpMessage = value.message
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.update { state ->
                            return@update state.copy(signUpInProgress = true, signedUp = false)
                        }
                    }
                    is Resource.Success -> {
                        if (value.data != null) {
                            repository.saveUser(listOf(value.data.user))
                            repository.upsertToken(value.data.accessToken)
                            _state.update { state ->
                                return@update state.copy(
                                    signUpError = false,
                                    signedUp = true,
                                    signUpInProgress = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(fName = _state.value.firstName)

        val lNameResult = Validator.validateLastName(lName = _state.value.lastName)

        val bDayResult = Validator.validateBirthDay(bDay = _state.value.birthDay)

        val emailResult = Validator.validateEmail(email = _state.value.email)

        val passwordResult = Validator.validatePassword(password = _state.value.password)

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = _state.value.privacyPolicyAccepted
        )

        val phoneResult = Validator.validatePhone(phone = _state.value.phone)

        _state.update { state ->
            return@update state.copy(
                firstNameError = fNameResult.status,
                lastNameError = lNameResult.status,
                emailError = emailResult.status,
                passwordError = passwordResult.status,
                privacyPolicyError = privacyPolicyResult.status,
                phoneError = phoneResult.status,
                birthDayError = bDayResult.status,
                allValidationsPassed =
                fNameResult.status &&
                    lNameResult.status &&
                    emailResult.status &&
                    passwordResult.status &&
                    privacyPolicyResult.status &&
                    phoneResult.status
            )
        }
    }

    private fun printState() {
        Log.d(TAG, "printState: ${state.value}")
    }

    private fun reset() {
        _state.update { state ->
            return@update state.copy(
                firstName = "",
                lastName = "",
                email = "",
                birthDay = "",
                phone = "",
                createdAt = "",
                fullName = "",
                password = "",
                role = "user",
                privacyPolicyAccepted = false,
                signUpMessage = "",

                firstNameError = false,
                lastNameError = false,
                birthDayError = false,
                emailError = false,
                phoneError = false,
                passwordError = false,
                privacyPolicyError = false,
                signUpError = false,
                allValidationsPassed = false,
                signUpInProgress = false
            )
        }
    }
}
