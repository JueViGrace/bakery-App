package com.bakery.bakeryapp.data.viewmodel.login.event

sealed class UIEvent {

    data class FirstNameChanged(val firstName: String) : UIEvent()

    data class LastNameChanged(val lastName: String) : UIEvent()

    data class BirthDayChanged(val birthDay: String) : UIEvent()

    data class EmailChanged(val email: String) : UIEvent()

    data class PhoneChanged(val phone: String) : UIEvent()

    data class PasswordChanged(val password: String) : UIEvent()

    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : UIEvent()

    data object RegisterButtonClicked : UIEvent()
}
