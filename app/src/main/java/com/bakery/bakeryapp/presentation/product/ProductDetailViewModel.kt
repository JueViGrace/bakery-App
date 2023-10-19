package com.bakery.bakeryapp.presentation.product

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.constantes.Constantes.PRODUCT_ID_KEY
import com.bakery.bakeryapp.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    // private val repository: MainRepository,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    savedStateHandle: SavedStateHandle,
    context: Context
) : ViewModel() {

    private var _state = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val state: StateFlow<ProductDetailState> = _state.asStateFlow()

    private val productError = context.getString(R.string.data_not_found)

    init {
        val productId = savedStateHandle.get<String>(PRODUCT_ID_KEY)
        getProductDetail(productId)
    }

    private fun getProductDetail(productId: String?) {
        productId?.let { id ->
            getProductDetailUseCase.invoke(id).onEach { list ->
                when (list) {
                    is Resource.Error -> _state.value = ProductDetailState.Error(error = productError)
                    is Resource.Loading -> _state.value = ProductDetailState.Loading
                    is Resource.Success -> {
                        if (list.data != null) {
                            list.data.forEach { item ->
                                _state.value = ProductDetailState.Success(success = item)
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
        /*viewModelScope.launch {
            _state.value = ProductDetailState.Loading
            productId?.let { id ->
                repository.getProductDetail(id).collect { list ->
                    if (list.isNotEmpty()) {
                        list.forEach { productsWithCategories ->
                            _state.value = ProductDetailState.Success(success = productsWithCategories)
                        }
                    } else {
                        _state.value = ProductDetailState.Error(error = productError)
                    }
                }
            }
        }*/
    }
}
