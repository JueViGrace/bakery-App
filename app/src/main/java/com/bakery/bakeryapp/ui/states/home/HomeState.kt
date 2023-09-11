package com.bakery.bakeryapp.ui.states.home

import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.User

data class HomeState(
    val user: User? = User("", "", "", "", "", "", "", "", ""),
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
    var loadingError: Boolean = false,
    var loadingMessage: String? = ""
)
