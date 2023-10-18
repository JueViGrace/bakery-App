package com.bakery.bakeryapp.presentation.home.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bakery.bakeryapp.data.repository.datastore.DataStoreViewModel
import com.bakery.bakeryapp.navigation.detailgraph.DetailScreen
import com.bakery.bakeryapp.navigation.homegraph.HomeNavGraph
import com.bakery.bakeryapp.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    boxScrollState: ScrollState = rememberScrollState(),
) {
    // topBar visibility state
    val topBarVisibilityState = remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            AppBar(
                isVisible = topBarVisibilityState.value,
                searchCharSequence = {
                },
                onCartIconClick = {
                    navController.navigate(DetailScreen.CartScreen.route)
                },
                /*onNotificationIconClick = {
                    navController.navigate(DetailScreen.NotificationScreen.route)
                }*/
            )
        },
        bottomBar = {
            BottomBar(navController = navController) { isVisible ->
                topBarVisibilityState.value = isVisible
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(boxScrollState)
        ) {
            HomeNavGraph(navController = navController)
        }
    }
}
