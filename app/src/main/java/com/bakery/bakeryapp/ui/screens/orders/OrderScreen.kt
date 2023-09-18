package com.bakery.bakeryapp.ui.screens.orders

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.ui.components.OrderComponent

@Composable
fun OrderScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        OrderComponent()
    }
}
