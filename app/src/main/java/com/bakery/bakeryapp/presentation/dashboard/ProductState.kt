package com.bakery.bakeryapp.presentation.dashboard

import com.bakery.bakeryapp.domain.model.product.Product

sealed class ProductState {
    data object Loading : ProductState()
    data class Error(val error: String) : ProductState()
    data class Success(val success: List<Product>) : ProductState()
}
