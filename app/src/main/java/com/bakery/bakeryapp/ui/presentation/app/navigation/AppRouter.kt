package com.bakery.bakeryapp.ui.presentation.app.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    data object SingUpScreen : Screen()
    data object TermsAndConditionsScreen : Screen()
    data object LoginScreen : Screen()
}

object AppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.SingUpScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
