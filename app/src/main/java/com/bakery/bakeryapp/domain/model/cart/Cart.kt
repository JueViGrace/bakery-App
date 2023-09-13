package com.bakery.bakeryapp.domain.model.cart

data class Cart(
    val _id: String,
    val createdAt: String?,
    val products: List<String>?,
    val status: Int?
)
