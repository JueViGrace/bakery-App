package com.bakery.bakeryapp.ui.screens.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.data.repository.datastore.DataStoreViewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.viewmodel.download.DownloadViewModel

@Composable
fun LoadingScreen(
    downloadViewModel: DownloadViewModel = viewModel(),
    dataStoreViewModel: DataStoreViewModel = viewModel()
) {
    dataStoreViewModel.getAccessToken()
    dataStoreViewModel.getCodigoUsuario()

    downloadViewModel.state.value.userId = dataStoreViewModel.state.value.codigo
    downloadViewModel.state.value.accessToken = dataStoreViewModel.state.value.accessToken

    if (downloadViewModel.loadingInProgress.value) {
        downloadViewModel.onDownload()
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
    } else {
        AppRouter.navigateTo(Screen.HomeScreen)
    }
}
