package com.bakery.bakeryapp.application

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.screens.cart.CartScreen
import com.bakery.bakeryapp.ui.screens.home.HomeScreen
import com.bakery.bakeryapp.ui.screens.loading.LoadingScreen
import com.bakery.bakeryapp.ui.screens.login.LoginScreen
import com.bakery.bakeryapp.ui.screens.orders.OrderScreen
import com.bakery.bakeryapp.ui.screens.product.ProductScreen
import com.bakery.bakeryapp.ui.screens.signup.SingUpScreen
import com.bakery.bakeryapp.ui.screens.terms.TermsAndConditionsScreen
import com.bakery.bakeryapp.ui.viewmodel.download.DownloadViewModel

@Composable
fun BakeryAppComposable(downloadViewModel: DownloadViewModel = viewModel()) {
    downloadViewModel.checkForActiveSession()

    downloadViewModel.isUserLoggedIn.observeForever { value ->
        downloadViewModel.loginInProgress.value = value == true
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
    }
}
