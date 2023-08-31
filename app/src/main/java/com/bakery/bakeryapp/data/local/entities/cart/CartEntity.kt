package com.bakery.bakeryapp.data.local.entities.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "products") val products: String,
    @ColumnInfo(name = "status") val status: Int?
)
