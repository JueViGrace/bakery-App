package com.bakery.bakeryapp.presentation.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.common.toDomainProduct
import com.bakery.bakeryapp.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository,
    context: Context
) : ViewModel() {
    private val _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state.asStateFlow()

    private val errorMessage = context.getString(R.string.products_not_found)

    init {
        getProduct()
    }

    private fun getProduct() {
        viewModelScope.launch {
            repository.getProducts().collect { list ->
                when (list) {
                    is Resource.Error -> {
                        _state.value = ProductState.Error(error = list.message ?: "No posee conexión a internet")
                        getProductFromDB()
                    }
                    is Resource.Loading -> {
                        _state.value = ProductState.Loading
                    }
                    is Resource.Success -> {
                        if (list.data != null) {
                            repository.saveProducts(list.data)
                            _state.value = ProductState.Success(list.data)
                        }
                    }
                }
            }
        }
    }

    private fun getProductFromDB() {
        viewModelScope.launch {
            _state.value = ProductState.Loading
            repository.getProductsFromDB().collect { list ->
                if (list.isNotEmpty()) {
                    list.forEach { productsWithCategories ->
                        if (productsWithCategories.products._id.isNotEmpty()) {
                            _state.value = ProductState.Success(
                                listOf(productsWithCategories.products.toDomainProduct())
                            )
                        }
                    }
                } else {
                    _state.value = ProductState.Error(errorMessage)
                }
            }
        }
    }
}
