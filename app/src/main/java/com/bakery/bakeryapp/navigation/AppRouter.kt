package com.bakery.bakeryapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object SingUpScreen : Screen()
    data object TermsAndConditionsScreen : Screen()
    data object LoginScreen : Screen()
    data object LoadingScreen : Screen()
    data object HomeScreen : Screen()
    data object ProductScreen : Screen()
    data object CartScreen : Screen()
    data object OrderScreen : Screen()
}

object AppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
