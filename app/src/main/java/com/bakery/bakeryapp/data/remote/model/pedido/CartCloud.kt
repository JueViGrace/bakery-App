package com.bakery.bakeryapp.data.remote.model.pedido

data class CartCloud(
    val _id: String,
    val createdAt: String,
    val products: List<ProductCloud>,
    val status: Int
)