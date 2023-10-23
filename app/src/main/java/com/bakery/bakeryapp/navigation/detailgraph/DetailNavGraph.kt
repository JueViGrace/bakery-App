package com.bakery.bakeryapp.navigation.detailgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bakery.bakeryapp.common.navigateSingleTopTo
import com.bakery.bakeryapp.constantes.Constantes.PRODUCT_ID_KEY
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.navigation.homegraph.ShopHomeScreen
import com.bakery.bakeryapp.presentation.cart.ui.CartScreen
import com.bakery.bakeryapp.presentation.product.component.ProductDetailScreen

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.ProductDetailScreen.route + "/{$PRODUCT_ID_KEY}"
    ) {
        composable(route = DetailScreen.CartScreen.route) {
            CartScreen(
                onBackPressed = {
                    navController.navigateSingleTopTo(ShopHomeScreen.DashboardScreen.route)
                },
                checkOutPressed = {
                }
            )
        }
        composable(route = DetailScreen.NotificationScreen.route) {}
        composable(
            route = DetailScreen.ProductDetailScreen.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") { defaultValue = "" }
            )
        ) {
            ProductDetailScreen {
                navController.popBackStack()
            }
        }
    }
}
