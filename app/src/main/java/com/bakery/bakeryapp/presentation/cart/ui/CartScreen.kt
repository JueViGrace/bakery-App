package com.bakery.bakeryapp.presentation.cart.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.presentation.components.CartComponent

@Composable
fun CartScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CartComponent()
    }
}
