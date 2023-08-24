package com.bakery.bakeryapp.data.remote.model.pedido

data class PedidoResponseItem(
    val __v: Int?,
    val _id: String,
    val cart: CartCloud,
    val createdAt: String,
    val status: Int?,
    val user: UserCloud
)