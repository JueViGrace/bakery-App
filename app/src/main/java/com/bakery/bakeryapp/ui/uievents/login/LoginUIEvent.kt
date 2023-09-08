package com.bakery.bakeryapp.ui.uievents.login

sealed class LoginUIEvent {

    data class EmailChanged(val email: String) : LoginUIEvent()

    data class PasswordChanged(val password: String) : LoginUIEvent()

    data object LogingButtonClicked : LoginUIEvent()
}
