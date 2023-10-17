package com.bakery.bakeryapp.presentation.signup.state

data class SignUpUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var birthDay: String = "",
    var phone: String = "",
    var createdAt: String = "",
    var fullName: String = "",
    var password: String = "",
    var role: String = "user",
    var userId: String = "",
    var privacyPolicyAccepted: Boolean = false,
    var singUpMessage: String? = "",
    var accessToken: String = "",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var birthDayError: Boolean = false,
    var emailError: Boolean = false,
    val phoneError: Boolean = false,
    var passwordError: Boolean = false,
    var privacyPolicyError: Boolean = false,
    var singUpError: Boolean = false,
    val allValidationsPassed: Boolean = false,
    val singUpInProgress: Boolean = false,
    val singedUp: Boolean = false
)