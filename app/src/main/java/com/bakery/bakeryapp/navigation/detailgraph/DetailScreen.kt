package com.bakery.bakeryapp.navigation.detailgraph

import androidx.navigation.NamedNavArgument

sealed class DetailScreen(val route: String, val arguments: List<NamedNavArgument>) {
    data object ProductScreen : DetailScreen(
        route = "ProductScreen",
        arguments = emptyList()
    )
    data object CartScreen : DetailScreen(
        route = "CartScreen",
        arguments = emptyList()
    )
    data object NotificationScreen : DetailScreen(
        route = "NotificationScreen",
        arguments = emptyList()
    )
}
