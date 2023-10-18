package com.bakery.bakeryapp.navigation.homegraph

import androidx.navigation.NamedNavArgument

sealed class ShopHomeScreen(val route: String, val arguments: List<NamedNavArgument>) {
    data object DashboardScreen : ShopHomeScreen(
        route = "DashboardScreen",
        arguments = emptyList()
    )
    data object OrderScreen : ShopHomeScreen(
        route = "OrderScreen",
        arguments = emptyList()
    )
    data object ProfileScreen : ShopHomeScreen(
        route = "ProfileScreen",
        arguments = emptyList()
    )
}
