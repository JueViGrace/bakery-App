package com.bakery.bakeryapp.ui.presentation.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.ui.presentation.app.navigation.AppRouter
import com.bakery.bakeryapp.ui.presentation.app.navigation.Screen
import com.bakery.bakeryapp.ui.presentation.app.screens.home.HomeScreen
import com.bakery.bakeryapp.ui.presentation.app.screens.login.LoginScreen
import com.bakery.bakeryapp.ui.presentation.app.screens.signup.SingUpScreen
import com.bakery.bakeryapp.ui.presentation.app.screens.terms.TermsAndConditionsScreen

@Composable
fun BakeryAppComposable() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Crossfade(targetState = AppRouter.currentScreen, label = "") { currentState ->
            when (currentState.value) {
                is Screen.SingUpScreen -> {
                    SingUpScreen()
                }

                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
            }
        }
    }
}
