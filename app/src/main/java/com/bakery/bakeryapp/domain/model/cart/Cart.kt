package com.bakery.bakeryapp.domain.model.cart

import com.bakery.bakeryapp.domain.model.product.Product

data class Cart(
    val _id: String,
    val createdAt: String,
    val products: List<Product>,
    val status: Int?
)