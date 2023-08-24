package com.bakery.bakeryapp.data.remote.model.cart

data class CartResponseItem(
    val __v: Int?,
    val _id: String,
    val createdAt: String,
    val products: List<String>,
    val status: Int?
)