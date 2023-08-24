package com.bakery.bakeryapp.domain.model.product

import com.bakery.bakeryapp.domain.model.category.Category

data class Product(
    val _id: String,
    val categories: List<Category>,
    val createdAt: String,
    val haveStock: Boolean,
    val name: String,
    val price: Double,
    val productImage: String,
    val stock: Int,
    val truePrice: Int
)
