package com.bakery.bakeryapp.data.remote.model.product

data class ProductResponseItem(
    val __v: Int?,
    val _id: String,
    val categories: List<String>,
    val createdAt: String,
    val haveStock: Boolean,
    val name: String,
    val price: Double,
    val productImage: String,
    val stock: Int,
    val truePrice: Int
)
