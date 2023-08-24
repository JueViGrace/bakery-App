package com.bakery.bakeryapp.data.remote.model.pedido

data class ProductCloud(
    val _id: String,
    val categories: List<CategoryCloud>,
    val createdAt: String,
    val haveStock: Boolean,
    val name: String,
    val price: String,
    val productImage: String,
    val stock: String,
    val truePrice: String
)