package com.bakery.bakeryapp.navigation.authgraph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.presentation.login.ui.LoginScreen
import com.bakery.bakeryapp.presentation.signup.ui.SingUpScreen
import com.bakery.bakeryapp.presentation.terms.TermsAndConditionsScreen

fun NavGraphBuilder.authNavGraph(navController: NavController, width: Int) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.LoginScreen.route
    ) {
        addLogin(navController, width)
        addSignUp(navController, width)
        addTerms(navController, width)
    }
}

fun NavGraphBuilder.addLogin(
    navController: NavController,
    width: Int
) {
    composable(
        route = AuthScreen.LoginScreen.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -width },
                animationSpec = tween(
                    durationMillis = 500, easing = FastOutSlowInEasing
                )
            ) + fadeOut(
                animationSpec = tween(durationMillis = 500)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -width },
                animationSpec = tween(
                    durationMillis = 500, easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(durationMillis = 500))
        },
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { -width },
                animationSpec = tween(
                    durationMillis = 500, easing = FastOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = 500)
            )
        }
    ) {
        LoginScreen(
            navigateToRegister = {
                navController.navigate(AuthScreen.SignUpScreen.route)
            },
            navigateToHome = {
                navController.navigate(Graph.HOME)
            }
        )
    }
}

fun NavGraphBuilder.addSignUp(
    navController: NavController,
    width: Int
) {
    composable(
        route = AuthScreen.SignUpScreen.route,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 500, easing = FastOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = 500)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 500, easing = FastOutSlowInEasing
                )
            ) + fadeOut(
                animationSpec = tween(durationMillis = 500)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { width },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(durationMillis = 500))
        }
    ) {
        SingUpScreen(
            navigateToHome = {
                navController.navigate(Graph.HOME)
            },
            navigateToLogin = {
                navController.navigate(AuthScreen.LoginScreen.route) {
                    popUpTo(AuthScreen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            },
            navigateToTerms = {
                navController.navigate(AuthScreen.TermsAndConditionsScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.addTerms(
    navController: NavController,
    width: Int
) {
    composable(
        route = AuthScreen.TermsAndConditionsScreen.route,
        enterTransition = {
            slideInVertically(
                initialOffsetY = { -width },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = 500)
            )
        },
        popExitTransition = {
            slideOutVertically(
                targetOffsetY = { width },
                animationSpec = tween(
                    durationMillis = 500,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(durationMillis = 500))
        }
    ) {
        TermsAndConditionsScreen()
    }
}
