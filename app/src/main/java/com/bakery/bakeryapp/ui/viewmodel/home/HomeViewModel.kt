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
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.NavigationItem
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.states.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase,
) : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    val state = mutableStateOf(HomeState())

    var loginInProgress = mutableStateOf(true)

    private var loadingCategories = mutableStateOf(true)

    private var loadingProducts = mutableStateOf(true)

    var loadingDataInProgress = mutableStateOf(true)

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

    /*fun getUserData(accessToken: String) {
        viewModelScope.launch {
            if (loadingDataInProgress.value) {
                val resultProducts = async {
                    repository.getProducts(accessToken)
                }.await()

                resultProducts.collect {
                    when (it) {
                        is Resource.Success -> {
                            if (it.data != null) {
                                repository.saveProducts(it.data)
                            } else {
                                state.value.loadingError = true
                            }
                        }

                        is Resource.Error -> {
                            loadingDataInProgress.value = false
                            state.value.loadingError = true
                            state.value = state.value.copy(
                                loadingMessage = it.message
                            )
                        }

                        is Resource.Loading -> {
                            loadingDataInProgress.value = true
                        }
                    }
                }
            }
        }
    }
*/

    fun showData() {
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
            repository.getCategoriesFromDB().collectLatest { list ->
                if (list.isNotEmpty()) {
                    state.value = state.value.copy(
                        categories = list
                    )
                }
            }

            repository.getProductsFromDB().collectLatest { list ->
                if (list.isNotEmpty()) {
                    state.value = state.value.copy(
                        products = list
                    )
                }
            }

            loadingDataInProgress.value = false
        }
    }
}
