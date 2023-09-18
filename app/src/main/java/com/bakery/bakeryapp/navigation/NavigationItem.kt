package com.bakery.bakeryapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val description: String,
    val itemId: Screen,
    val icon: ImageVector,
)
