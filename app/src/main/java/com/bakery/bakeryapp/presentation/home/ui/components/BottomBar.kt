package com.bakery.bakeryapp.presentation.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bakery.bakeryapp.navigation.detailgraph.DetailScreen
import com.bakery.bakeryapp.navigation.homegraph.ShopHomeScreen
import com.bakery.bakeryapp.presentation.home.ui.BottomNavItem

@Composable
fun BottomBar(
    navController: NavController,
    isVisible: (Boolean) -> Unit,
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
        BottomNavItem.OrderNav,
        BottomNavItem.ProfileNav,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf(true) }

    if (bottomNavVisibility) {
        BottomAppBar(
            containerColor = Color.White,
            modifier = Modifier
                .background(color = Color.White)
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))

        ) {
            navItemList.forEach { screen ->
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                LocalContentColor.current
                            }
                        )
                    },
                    //  label = { Text(text = screen.tittle) },
                    onClick = {
                        navController.navigate(screen.route)
                    }
                )
            }
        }
    }

    // hide topBar
    when (navBackStackEntry?.destination?.route) {
        ShopHomeScreen.DashboardScreen.route -> {
            bottomNavVisibility = true
            isVisible(true)
        }
        DetailScreen.ProductScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        DetailScreen.CartScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        else -> {
            bottomNavVisibility = true
            isVisible(false)
        }
    }
}

/*@Composable
fun BottomBar(
    bottomDrawerItem: List<NavigationItem>,
    onNavigationItemClicked: (NavigationItem) -> Unit
) {
    val currentRoute = AppRouter.currentScreen.value

    BottomAppBar(actions = {
        bottomDrawerItem.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.itemId,
                onClick = { onNavigationItemClicked.invoke(navItem) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.description
                    )
                },
                label = {
                    Text(text = navItem.title, color = MaterialTheme.colorScheme.onPrimary)
                },
                alwaysShowLabel = false
            )
        }
    })
}*/
