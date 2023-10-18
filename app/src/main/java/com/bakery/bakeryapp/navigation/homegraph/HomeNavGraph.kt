package com.bakery.bakeryapp.navigation.homegraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bakery.bakeryapp.navigation.Graph
import com.bakery.bakeryapp.navigation.detailgraph.DetailScreen
import com.bakery.bakeryapp.navigation.detailgraph.detailNavGraph
import com.bakery.bakeryapp.presentation.dashboard.component.DashboardScreen
import com.bakery.bakeryapp.presentation.orders.ui.OrderScreen
import com.bakery.bakeryapp.presentation.profile.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = ShopHomeScreen.DashboardScreen.route
    ) {
        composable(route = ShopHomeScreen.DashboardScreen.route) {
            DashboardScreen {
                navController.navigate(DetailScreen.ProductScreen.route)
            }
        }
        composable(route = ShopHomeScreen.OrderScreen.route) {
            OrderScreen()
        }
        composable(route = ShopHomeScreen.ProfileScreen.route) {
            ProfileScreen {
                navController.popBackStack()
            }
        }
        detailNavGraph(navController = navController)
    }
}
