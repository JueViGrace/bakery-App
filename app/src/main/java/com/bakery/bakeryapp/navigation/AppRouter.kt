package com.bakery.bakeryapp.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object AppRouter {
    val currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}
