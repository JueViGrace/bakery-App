package com.bakery.bakeryapp.presentation.auth.login.events

sealed class LoginUIEvent {

    data class EmailChanged(val email: String) : LoginUIEvent()

    data class PasswordChanged(val password: String) : LoginUIEvent()

    data object LogingButtonClicked : LoginUIEvent()
}
