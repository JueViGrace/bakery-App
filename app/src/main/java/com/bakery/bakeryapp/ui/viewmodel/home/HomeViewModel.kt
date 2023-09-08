package com.bakery.bakeryapp.ui.viewmodel.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.NavigationItem
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.ui.states.home.HomeState
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import com.bakery.bakeryapp.domain.usecase.GetUserFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase,
    private val getUserFromDBUseCase: GetUserFromDBUseCase
) : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    val state = mutableStateOf(HomeState())

    var loginInProgress = mutableStateOf(true)

    val emailId: MutableLiveData<String> = MutableLiveData()

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

    fun checkForActiveSession() {
        viewModelScope.launch {
            val result = repository.getUser()

            result.collectLatest {
                if (it.isNotEmpty()) {
                    Log.d(TAG, "Valid Session")
                    isUserLoggedIn.value = true
                } else {
                    Log.d(TAG, "User is not logged in")
                    isUserLoggedIn.value = false
                }
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            repository.getUser().collectLatest { list ->
                if (list.isNotEmpty()) {
                    list.forEach { user ->
                        state.value = state.value.copy(
                            user = user
                        )
                    }
                }
            }
        }
        emailId.value = state.value.user?.email
        Log.d(TAG, "getUserData: ${emailId.value}")
        Log.d(TAG, "getUserData: ${state.value}")
    }
}
