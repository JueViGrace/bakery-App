package com.bakery.bakeryapp.presentation.home.ui

import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.navigation.homegraph.ShopHomeScreen

sealed class BottomNavItem(val title: String, val icon: Int, val route: String){
    data object HomeNav : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_shop_icon,
        route = ShopHomeScreen.DashboardScreen.route
    )

    data object OrderNav : BottomNavItem(
        title = "Orders",
        icon = R.drawable.ic_bag,
        route = ShopHomeScreen.OrderScreen.route
    )

    data object ProfileNav : BottomNavItem(
        title = "Profile",
        icon = R.drawable.ic_user_icon,
        route = ShopHomeScreen.ProfileScreen.route
    )
}
