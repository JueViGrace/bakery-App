package com.bakery.bakeryapp.application

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun BakeryAppComposable() {
    val navController = rememberNavController()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
        }
    }

    /*Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
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

                    is Screen.LoadingScreen -> {
                        LoadingScreen()
                    }

                    is Screen.ProductScreen -> {
                        ProductScreen()
                    }

                    is Screen.CartScreen -> {
                        CartScreen()
                    }

                    is Screen.OrderScreen -> {
                        OrderScreen()
                    }
                }
            }
        }
    if (downloadViewModel.loginInProgress.value) {
        AppRouter.navigateTo(Screen.LoadingScreen)
    } else {
        AppRouter.navigateTo(Screen.LoginScreen)
    }
}*/
}
