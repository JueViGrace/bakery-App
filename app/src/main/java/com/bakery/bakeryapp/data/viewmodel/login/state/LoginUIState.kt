package com.bakery.bakeryapp.data.viewmodel.login.state

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val accessToken: String = "",
    var loginMessage: String? = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false,
    var loginError: Boolean = false
)
