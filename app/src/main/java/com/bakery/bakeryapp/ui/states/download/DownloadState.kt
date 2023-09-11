package com.bakery.bakeryapp.ui.states.download

import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product

data class DownloadState(
    var userId: String = "",
    var accessToken: String = "",
    val categories: List<Category> = emptyList(),
    val products: List<Product> = emptyList(),
    val cart: Cart = Cart("", "", "", null),
    val pedidos: List<Pedido> = emptyList(),
    var down: Int = 0,

    var loadingError: Boolean = false,
    var loadingMessage: String? = ""
)
