package com.bakery.bakeryapp.data.viewmodel.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.constantes.Constantes.formattedDate
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.data.rules.Validator
import com.bakery.bakeryapp.data.viewmodel.login.event.UIEvent
import com.bakery.bakeryapp.data.viewmodel.login.state.RegistrationUIState
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.ui.presentation.app.navigation.AppRouter
import com.bakery.bakeryapp.ui.presentation.app.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    val state = mutableStateOf(RegistrationUIState())

    val allValidationsPassed = mutableStateOf(false)

    fun onEvent(event: UIEvent) {
        when (event) {
            is UIEvent.FirstNameChanged -> {
                state.value = state.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is UIEvent.LastNameChanged -> {
                state.value = state.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is UIEvent.EmailChanged -> {
                state.value = state.value.copy(
                    email = event.email
                )
                printState()
            }

            is UIEvent.PasswordChanged -> {
                state.value = state.value.copy(
                    password = event.password
                )
                printState()
            }

            is UIEvent.RegisterButtonClicked -> {
                singUp()
            }

            is UIEvent.PrivacyPolicyCheckBoxClicked -> {
                state.value = state.value.copy(
                    privacyPolicyAccepted = event.status
                )
                printState()
            }

            is UIEvent.BirthDayChanged -> {
                state.value = state.value.copy(
                    birthDay = event.birthDay
                )
                printState()
            }

            is UIEvent.PhoneChanged -> {
                state.value = state.value.copy(
                    phone = event.phone
                )
                printState()
            }
        }
        validateDataWithRules()
    }

    private fun singUp() {
        validateDataWithRules()

        val register = Register(
            birthDate = state.value.birthDay,
            createdAt = formattedDate,
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

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(fName = state.value.firstName)

        val lNameResult = Validator.validateLastName(lName = state.value.lastName)

        val bDayResult = Validator.validateBirthDay(bDay = state.value.birthDay)

        val emailResult = Validator.validateEmail(email = state.value.email)

        val passwordResult = Validator.validatePassword(password = state.value.password)

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = state.value.privacyPolicyAccepted
        )

        val phoneResult = Validator.validatePhone(phone = state.value.phone)

        val fullNameResult =
            Validator.validateFullName(fName = "${state.value.firstName} ${state.value.lastName}")

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult: $fNameResult")
        Log.d(TAG, "lNameResult: $lNameResult")
        Log.d(TAG, "emailResult: $emailResult")
        Log.d(TAG, "passwordResult: $passwordResult")
        Log.d(TAG, "privacyPolicyResult: $privacyPolicyResult")
        Log.d(TAG, "bDayResult: $bDayResult")
        Log.d(TAG, "phoneResult: $phoneResult")
        Log.d(TAG, "fullNameResult: $fullNameResult")

        state.value = state.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status,
            phoneError = phoneResult.status,
            fullNameError = fullNameResult.status
        )

        allValidationsPassed.value =
            fNameResult.status && lNameResult.status && emailResult.status &&
            passwordResult.status && privacyPolicyResult.status && phoneResult.status && fullNameResult.status
    }

    private fun printState() {
        Log.d(TAG, "printState: ${state.value}")
    }

    private fun createUserInCloud(register: Register) {
        viewModelScope.launch {
            val result = async {
                repository.register(register)
            }.await()

            result.collect {
                when (it) {
                    is Resource.Success -> {
                        AppRouter.navigateTo(Screen.HomeScreen)
                    }

                    is Resource.Error -> {
                        state.value.singUpError = true
                        state.value = state.value.copy(singUpMessage = it.message)
                    }

                    is Resource.Loading -> {
                    }
                }
            }
        }
    }
}
