package com.bakery.bakeryapp.constantes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import com.bakery.bakeryapp.navigation.NavigationItem
import com.bakery.bakeryapp.navigation.Screen
import com.google.android.play.core.ktx.BuildConfig
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Constantes {
    const val version: String = BuildConfig.VERSION_NAME
    const val build: String = BuildConfig.BUILD_TYPE
    const val BASE_URL = "https://1318-45-186-203-254.ngrok-free.app/api/"
    const val UPDATE_APP_REQUEST_CODE: Int = 200
    const val DATABASE_VERSION_OLD: Int = 0
    const val DATABASE_VERSION_NEW: Int = 3
    const val DATABASE_NAME: String = "bakery_db"
    const val DATASTORE_NAME = "PREFERECES"
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    // variables para dar formato a los numeros
    val formato = DecimalFormat("#,##0.00")
    val formato2 = DecimalFormat("#,##0")
    const val ACCESS_TOKEN = "ACCESS_TOKEN_KEY"
    const val COD_USUARIO = "COD_USUARIO_KEY"
    const val NICK_USUARIO = "NICK_USUARIO_KEY"
    const val NOMBRE_USUARIO = "NOMBRE_USUARIO_KEY"
    const val CLEAR_PREFERENCES = "CLEAR_PREFERENCES_KEY"
    // fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val navigationList = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Filled.Home,
            description = "Home Screen",
            itemId = Screen.HomeScreen
        ),
        /*NavigationItem(
            title = "Categories",
            icon = Icons.Filled.Category,
            description = "Home Screen",
            itemId = "homeScreen"
        ),*/
        NavigationItem(
            title = "Products",
            icon = Icons.Default.ShoppingBag,
            description = "Home Screen",
            itemId = Screen.ProductScreen
        ),
        NavigationItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            description = "Home Screen",
            itemId = Screen.CartScreen
        ),
        NavigationItem(
            title = "Orders",
            icon = Icons.Default.AddShoppingCart,
            description = "Home Screen",
            itemId = Screen.OrderScreen
        ),
    )
}
