package com.bakery.bakeryapp.domain.model.pedido

import com.bakery.bakeryapp.domain.model.user.User
import com.bakery.bakeryapp.domain.model.cart.Cart

data class Pedido(
    val _id: String,
    val cart: Cart,
    val createdAt: String,
    val status: Int,
    val user: User
)