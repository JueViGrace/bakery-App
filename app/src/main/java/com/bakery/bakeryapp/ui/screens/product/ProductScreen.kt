package com.bakery.bakeryapp.ui.screens.product

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.ui.components.ProductComponent

@Composable
fun ProductScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        ProductComponent()
    }
}
