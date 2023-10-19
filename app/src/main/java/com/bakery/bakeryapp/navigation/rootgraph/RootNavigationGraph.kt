package com.bakery.bakeryapp.navigation.rootgraph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.navigation.authgraph.authNavGraph
import com.bakery.bakeryapp.presentation.home.ui.components.HomeScreen

@Composable
fun RootNavigationGraph(navController: NavHostController, width: Int) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION,
    ) {
        authNavGraph(
            navController = navController,
            width = width / 2
        )
        addHome(
            navController,
            width = width / 2
        )
    }
}

fun NavGraphBuilder.addHome(
    navController: NavHostController,
    width: Int
) {
    composable(
        route = Graph.HOME,
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
        HomeScreen()
    }
}
