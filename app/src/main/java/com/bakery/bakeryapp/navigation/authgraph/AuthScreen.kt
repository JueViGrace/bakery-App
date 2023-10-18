package com.bakery.bakeryapp.navigation.authgraph

import androidx.navigation.NamedNavArgument

sealed class AuthScreen(val route: String, val arguments: List<NamedNavArgument>) {
    data object SignUpScreen : AuthScreen(
        route = "SingUp",
        arguments = emptyList()
    )
    data object TermsAndConditionsScreen : AuthScreen(
        route = "Terms",
        arguments = emptyList()
    )
    data object LoginScreen : AuthScreen(
        route = "Login",
        arguments = emptyList()
    )
}
