package com.bakery.bakeryapp.presentation.home.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.presentation.home.state.HomeState
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

    val state = mutableStateOf(HomeState())

    var loadingDataInProgress = mutableStateOf(true)

    val emailId: MutableLiveData<String> = MutableLiveData()

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
            repository.getUser().collect { list ->
                if (list.isNotEmpty()) {
                    list.forEach { user ->
                        state.value = state.value.copy(
                            user = user
                        )
                    }
                }
            }
        }
        Log.d(TAG, "user: ${state.value.user}")

        loadingDataInProgress.value = false
    }

    fun showCategories() {
        viewModelScope.launch {
            repository.getCategoriesFromDB().collect { list ->
                if (list.isNotEmpty()) {
                    state.value = state.value.copy(
                        categories = list
                    )
                }
            }
        }
        state.value.categories.forEach { Log.d(TAG, "categories: $it") }
    }

    fun showProducts() {
        viewModelScope.launch {
            repository.getProductsFromDB().collect { list ->
                if (list.isNotEmpty()) {
                    state.value = state.value.copy(
                        products = list
                    )
                }
            }
        }
        state.value.products.forEach { Log.d(TAG, "prodcucts: $it") }
    }
}
