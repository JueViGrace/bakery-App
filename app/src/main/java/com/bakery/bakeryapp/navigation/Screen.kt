package com.bakery.bakeryapp.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>) {
    data object SingUpScreen : Screen(
        route = "SingUp",
        arguments = emptyList()
    )
    data object TermsAndConditionsScreen : Screen(
        route = "Terms",
        arguments = emptyList()
    )
    data object LoginScreen : Screen(
        route = "Login",
        arguments = emptyList()
    )
    data object LoadingScreen : Screen(
        route = "Loading",
        arguments = emptyList()
    )
    data object HomeScreen : Screen(
        route = "Home",
        arguments = emptyList()
    )
    data object ProductScreen : Screen(
        route = "Products",
        arguments = emptyList()
    )
    data object CartScreen : Screen(
        route = "Cart",
        arguments = emptyList()
    )
    data object OrderScreen : Screen(
        route = "Orders",
        arguments = emptyList()
    )
}
