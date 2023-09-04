package com.bakery.bakeryapp.data.viewmodel.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bakery.bakeryapp.data.viewmodel.login.event.UIEvent
import com.bakery.bakeryapp.data.viewmodel.login.state.RegistrationUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    // private val repository: MainRepository
) : ViewModel() {

    private val TAG = LoginViewModel::class.simpleName

    val state = mutableStateOf(RegistrationUIState())

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
        }
    }

    private fun singUp() {
        printState()
    }

    private fun printState() {
        Log.d(TAG, "printState: ${state.value}")
    }
}
