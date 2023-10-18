package com.bakery.bakeryapp.presentation.signup.state

import com.bakery.bakeryapp.common.toCustomFormat
import java.util.Date

data class SignUpUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var birthDay: String = "",
    var phone: String = "",
    var createdAt: String = Date().toCustomFormat(),
    var fullName: String = "",
    var password: String = "",
    var role: String = "user",
    var privacyPolicyAccepted: Boolean = false,
    var signUpMessage: String? = "",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var birthDayError: Boolean = false,
    var emailError: Boolean = false,
    val phoneError: Boolean = false,
    var passwordError: Boolean = false,
    var privacyPolicyError: Boolean = false,
    var signUpError: Boolean = false,
    val allValidationsPassed: Boolean = false,
    val signUpInProgress: Boolean = false,
    val signedUp: Boolean = false
)
