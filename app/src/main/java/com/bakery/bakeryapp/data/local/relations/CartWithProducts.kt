package com.bakery.bakeryapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity

data class CartWithProducts(
    @Embedded val cart: CartEntity,
    @Relation(parentColumn = "products", entityColumn = "_id")
    val products: List<ProductEntity>
)
