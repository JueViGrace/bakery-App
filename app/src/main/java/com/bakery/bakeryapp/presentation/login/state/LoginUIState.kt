package com.bakery.bakeryapp.presentation.login.state

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val accessToken: String = "",
    val userId: String = "",
    var loginMessage: String? = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    var loginError: Boolean = false,
    val allValidationsPassed: Boolean = false,
    val loginInProgress: Boolean = false,
    val loggedIn: Boolean = false
)
