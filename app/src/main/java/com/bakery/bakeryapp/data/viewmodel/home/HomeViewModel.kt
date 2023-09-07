package com.bakery.bakeryapp.data.viewmodel.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.data.navigation.AppRouter
import com.bakery.bakeryapp.data.navigation.NavigationItem
import com.bakery.bakeryapp.data.navigation.Screen
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase
) : ViewModel() {

    val navigationList = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Filled.Home,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Categories",
            icon = Icons.Filled.Category,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Products",
            icon = Icons.Default.ShoppingBag,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
        NavigationItem(
            title = "Orders",
            icon = Icons.Default.AddShoppingCart,
            description = "Home Screen",
            itemId = "homeScreen"
        ),
    )

    fun logOut() {
        viewModelScope.launch {
            deleteDataBaseUseCase.invoke()

            val user = repository.getUser()

            user.collectLatest {
                if (it.isEmpty()) {
                    AppRouter.navigateTo(Screen.LoginScreen)
                }
            }
        }
    }
}
