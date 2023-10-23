package com.bakery.bakeryapp.navigation.authgraph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bakery.bakeryapp.common.navigateSingleTopTo
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.presentation.auth.forgetpassword.ForgetPasswordScreen
import com.bakery.bakeryapp.presentation.auth.login.ui.LoginScreen
import com.bakery.bakeryapp.presentation.auth.signup.ui.SingUpScreen
import com.bakery.bakeryapp.presentation.auth.terms.TermsAndConditionsScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, width: Int) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.LoginScreen.route
    ) {
        addLogin(navController, width)
        addSignUp(navController, width)
        addTerms(navController, width)
        addForgetPassword(navController, width)
    }
}

fun NavGraphBuilder.addLogin(
    navController: NavHostController,
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
                navController.navigateSingleTopTo(AuthScreen.SignUpScreen.route)
            },
            navigateToHome = {
                navController.navigateSingleTopTo(Graph.HOME)
            },
            navigateToForgotPassword = {
                navController.navigateSingleTopTo(AuthScreen.ForgetPasswordScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.addSignUp(
    navController: NavHostController,
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
                navController.navigateSingleTopTo(Graph.HOME)
            },
            navigateToLogin = {
                navController.navigateSingleTopTo(AuthScreen.LoginScreen.route)
            },
            navigateToTerms = {
                navController.navigateSingleTopTo(AuthScreen.TermsAndConditionsScreen.route)
            }
        )
    }
}

fun NavGraphBuilder.addTerms(
    navController: NavHostController,
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

fun NavGraphBuilder.addForgetPassword(
    navController: NavHostController,
    width: Int
) {
    composable(
        route = AuthScreen.ForgetPasswordScreen.route,
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
        ForgetPasswordScreen(navController = navController)
    }
}
