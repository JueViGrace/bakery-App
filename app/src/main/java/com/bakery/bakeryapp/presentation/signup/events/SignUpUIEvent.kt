package com.bakery.bakeryapp.presentation.signup.events

sealed class SignUpUIEvent {

    data class FirstNameChanged(val firstName: String) : SignUpUIEvent()

    data class LastNameChanged(val lastName: String) : SignUpUIEvent()

    data class BirthDayChanged(val birthDay: String) : SignUpUIEvent()

    data class EmailChanged(val email: String) : SignUpUIEvent()

    data class PhoneChanged(val phone: String) : SignUpUIEvent()

    data class PasswordChanged(val password: String) : SignUpUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignUpUIEvent()

    data object RegisterButtonClicked : SignUpUIEvent()
}
