package com.bakery.bakeryapp.data.viewmodel.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.constantes.Constantes.formatter
import com.bakery.bakeryapp.data.navigation.AppRouter
import com.bakery.bakeryapp.data.navigation.Screen
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.data.rules.Validator
import com.bakery.bakeryapp.data.viewmodel.signup.event.SingUpUIEvent
import com.bakery.bakeryapp.data.viewmodel.signup.state.RegistrationUIState
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase
) : ViewModel() {

    private val TAG = SingUpViewModel::class.simpleName

    val state = mutableStateOf(RegistrationUIState())

    val allValidationsPassed = mutableStateOf(false)

    val singUpInProgress = mutableStateOf(false)

    private val date = mutableStateOf(Date())

    fun onEvent(event: SingUpUIEvent) {
        when (event) {
            is SingUpUIEvent.FirstNameChanged -> {
                state.value = state.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SingUpUIEvent.LastNameChanged -> {
                state.value = state.value.copy(
                    lastName = event.lastName,
                    fullName = "${state.value.firstName} ${event.lastName}"
                )
                printState()
            }

            is SingUpUIEvent.EmailChanged -> {
                state.value = state.value.copy(
                    email = event.email
                )
                printState()
            }

            is SingUpUIEvent.PasswordChanged -> {
                state.value = state.value.copy(
                    password = event.password
                )
                printState()
            }

            is SingUpUIEvent.RegisterButtonClicked -> {
                state.value = state.value.copy(
                    createdAt = formatter.format(date.value)
                )
                singUp()
            }

            is SingUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                state.value = state.value.copy(
                    privacyPolicyAccepted = event.status
                )
                printState()
            }

            is SingUpUIEvent.BirthDayChanged -> {
                state.value = state.value.copy(
                    birthDay = event.birthDay
                )
                printState()
            }

            is SingUpUIEvent.PhoneChanged -> {
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

        state.value = state.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status,
            phoneError = phoneResult.status,
            birthDayError = bDayResult.status
        )

        allValidationsPassed.value =
            fNameResult.status && lNameResult.status && emailResult.status &&
            passwordResult.status && privacyPolicyResult.status && phoneResult.status
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
                        if (it.data != null) {
                            state.value = state.value.copy(
                                accessToken = it.data.access_token
                            )
                            repository.saveUser(listOf(it.data.user))
                            AppRouter.navigateTo(Screen.HomeScreen)
                            singUpInProgress.value = false
                            reset()
                        } else {
                            state.value.singUpError = true
                        }
                    }

                    is Resource.Error -> {
                        state.value.singUpError = true
                        singUpInProgress.value = false
                        state.value = state.value.copy(singUpMessage = it.message)
                    }

                    is Resource.Loading -> {
                        singUpInProgress.value = true
                    }
                }
            }
        }
    }

    private fun reset() {
        state.value = state.value.copy(
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
            singUpMessage = "",
            accessToken = "",

            firstNameError = false,
            lastNameError = false,
            birthDayError = false,
            emailError = false,
            phoneError = false,
            passwordError = false,
            privacyPolicyError = false,
            singUpError = false
        )
        allValidationsPassed.value = false
        singUpInProgress.value = false
    }

    fun logOut() {
        viewModelScope.launch {
            deleteDataBaseUseCase.invoke()

            val user = repository.getUser()

            user.collectLatest {
                if (it.isEmpty()) {
                    AppRouter.navigateTo(Screen.LoginScreen)
                }
            }
        }
    }
}
