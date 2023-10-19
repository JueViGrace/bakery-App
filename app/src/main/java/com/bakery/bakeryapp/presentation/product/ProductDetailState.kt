package com.bakery.bakeryapp.presentation.product

import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories

sealed class ProductDetailState {
    data object Loading : ProductDetailState()
    data class Error(val error: String) : ProductDetailState()
    data class Success(val success: ProductsWithCategories) : ProductDetailState()
}
