package com.bakery.bakeryapp.navigation.detailgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.presentation.cart.ui.CartScreen
import com.bakery.bakeryapp.presentation.product.ui.ProductScreen

fun NavGraphBuilder.detailNavGraph(navController: NavController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.ProductScreen.route
    ) {
        composable(route = DetailScreen.CartScreen.route) {
            CartScreen()
        }
        composable(route = DetailScreen.NotificationScreen.route) {
        }
        composable(route = DetailScreen.ProductScreen.route) {
            ProductScreen()
        }
    }
}
