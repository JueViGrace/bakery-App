package com.bakery.bakeryapp.navigation.detailgraph

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class DetailScreen(val route: String, val arguments: List<NamedNavArgument>) {
    data object ProductDetailScreen : DetailScreen(
        route = "ProductDetailScreen",
        arguments = emptyList()
    )
    data object CartScreen : DetailScreen(
        route = "CartScreen",
        arguments = emptyList()
    )
    data object NotificationScreen : DetailScreen(
        route = "NotificationScreen",
        arguments = listOf(navArgument("productId") { type = NavType.StringType })
    )
}
