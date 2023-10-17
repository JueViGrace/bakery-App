package com.bakery.bakeryapp.presentation.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.constantes.Constantes.ACCESS_TOKEN
import com.bakery.bakeryapp.constantes.Constantes.navigationList
import com.bakery.bakeryapp.data.repository.datastore.DataStoreViewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.presentation.components.AppToolbar
import com.bakery.bakeryapp.presentation.components.BottomBar
import com.bakery.bakeryapp.presentation.download.DownloadViewModel
import com.bakery.bakeryapp.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    downloadViewModel: DownloadViewModel = viewModel(),
    dataStoreViewModel: DataStoreViewModel = viewModel()
) {
    // val scaffoldState = rememberScaffoldState()
    // val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        /*if (homeViewModel.loadingDataInProgress.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }*/

        Scaffold(
            // scaffoldState = scaffoldState,
            topBar = {
                AppToolbar(
                    toolbarTitle = stringResource(id = R.string.home),
                    logoutButtonClicked = {
                        homeViewModel.logOut()
                        dataStoreViewModel.clearPreferences(ACCESS_TOKEN)
                    },
                    /*navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }*/
                )
            },
            bottomBar = {
                BottomBar(
                    bottomDrawerItem = navigationList,
                    onNavigationItemClicked = {
                        AppRouter.navigateTo(it.itemId)
                    }
                )
            }
            /*drawerContent = {
            NavigationDrawerHeader(value = homeViewModel.emailId.value)
            NavigationDrawerBody(
                navigationDrawerItem = homeViewModel.navigationList,
                onNavigationItemClicked = {
                }
            )
        }*/
        ) { paddingValues ->
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(onClick = { downloadViewModel.onDownload() }) {
                        Text(text = "getAPi")
                    }
                    Button(onClick = { homeViewModel.showData() }) {
                        Text(text = "getDB")
                    }
                    Button(onClick = { homeViewModel.showCategories() }) {
                        Text(text = "getCategory")
                    }
                    Button(onClick = { homeViewModel.showProducts() }) {
                        Text(text = "getProducts")
                    }
                }
            }
        }
    }
}
