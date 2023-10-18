package com.bakery.bakeryapp.presentation.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bakery.bakeryapp.navigation.rootgraph.RootNavigationGraph

@Composable
fun BakeryAppComposable() {
    val navController = rememberNavController()
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            RootNavigationGraph(navController = navController, width = constraints.maxWidth)
        }
    }
}
