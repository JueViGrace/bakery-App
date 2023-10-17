package com.bakery.bakeryapp.presentation.orders.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.presentation.components.OrderComponent

@Composable
fun OrderScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        OrderComponent()
    }
}
