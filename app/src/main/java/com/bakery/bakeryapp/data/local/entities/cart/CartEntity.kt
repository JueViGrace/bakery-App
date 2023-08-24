package com.bakery.bakeryapp.data.local.entities.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "products") val products: List<ProductEntity>,
    @ColumnInfo(name = "status") val status: Int
)
