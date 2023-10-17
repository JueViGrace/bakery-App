package com.bakery.bakeryapp.application

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.presentation.home.ui.HomeScreen
import com.bakery.bakeryapp.presentation.home.viewmodel.HomeViewModel
import com.bakery.bakeryapp.presentation.login.ui.LoginScreen
import com.bakery.bakeryapp.presentation.login.viewmodel.LoginViewModel
import com.bakery.bakeryapp.presentation.signup.ui.SingUpScreen
import com.bakery.bakeryapp.presentation.signup.viewmodel.SingUpViewModel

@Composable
fun BakeryAppComposable() {
    val navController = rememberNavController()
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.LoginScreen.route,
            ) {
                addLogin(
                    navController = navController,
                    width = constraints.maxWidth / 2
                )
                addSingUp(
                    navController = navController,
                    width = constraints.maxWidth / 2
                )
                addHome(
                    navController,
                    width = constraints.maxWidth / 2
                )
            }
        }
    }
}

fun NavGraphBuilder.addLogin(
    navController: NavController,
    width: Int
) {
    composable(
        route = Screen.LoginScreen.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -width },
                animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -width },
                animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(durationMillis = 300))
        }
    ) {
        val loginViewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            loginViewModel = loginViewModel,
            navigateToRegister = {
                navController.navigate(Screen.SingUpScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.addSingUp(
    navController: NavController,
    width: Int
) {
    composable(
        route = Screen.SingUpScreen.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = 300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeOut(
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(durationMillis = 300))
        }
    ) {
        val viewModel: SingUpViewModel = hiltViewModel()
        SingUpScreen(
            state = viewModel.state.value,
            events = viewModel::onEvent,
            navigateToHome = { singedUp ->
                if (singedUp) {
                    navController.navigate(Screen.HomeScreen.route)
                    navController.popBackStack()
                }
            },
            navigateToLogin = {
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

fun NavGraphBuilder.addHome(
    navController: NavController,
    width: Int
) {
    composable(
        route = Screen.HomeScreen.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 300, easing = FastOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = 300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(durationMillis = 300))
        }
    ) {
        val viewModel: HomeViewModel = hiltViewModel()
        HomeScreen()
    }
}
