package com.bakery.bakeryapp.data.viewmodel.signup.event

sealed class SingUpUIEvent {

    data class FirstNameChanged(val firstName: String) : SingUpUIEvent()

    data class LastNameChanged(val lastName: String) : SingUpUIEvent()

    data class BirthDayChanged(val birthDay: String) : SingUpUIEvent()

    data class EmailChanged(val email: String) : SingUpUIEvent()

    data class PhoneChanged(val phone: String) : SingUpUIEvent()

    data class PasswordChanged(val password: String) : SingUpUIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SingUpUIEvent()

    data object RegisterButtonClicked : SingUpUIEvent()
}
