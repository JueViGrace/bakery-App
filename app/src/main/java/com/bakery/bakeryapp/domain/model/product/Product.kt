package com.bakery.bakeryapp.domain.model.product

data class Product(
    val _id: String,
    var categories: List<String>,
    val createdAt: String,
    val haveStock: Boolean,
    val name: String,
    val price: Double,
    val productImage: String,
    val stock: Int,
    val truePrice: Int
)
