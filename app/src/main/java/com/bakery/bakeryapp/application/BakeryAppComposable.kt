package com.bakery.bakeryapp.application

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.screens.home.HomeScreen
import com.bakery.bakeryapp.ui.screens.login.LoginScreen
import com.bakery.bakeryapp.ui.screens.signup.SingUpScreen
import com.bakery.bakeryapp.ui.screens.terms.TermsAndConditionsScreen
import com.bakery.bakeryapp.ui.viewmodel.home.HomeViewModel

@Composable
fun BakeryAppComposable(homeViewModel: HomeViewModel = viewModel()) {
    homeViewModel.checkForActiveSession()

    homeViewModel.isUserLoggedIn.observeForever { value ->
        if (value == true) {
            AppRouter.navigateTo(Screen.HomeScreen)
            homeViewModel.loginInProgress.value = false
        }
    }

    Box(
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
                }
            }
        }
        if (homeViewModel.loginInProgress.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
