package com.bakery.bakeryapp.data.local.entities.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bakery.bakeryapp.data.local.converters.Converters

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "createdAt") val createdAt: String?,
    @ColumnInfo(name = "products")
    @TypeConverters(Converters::class)
    val products: List<String>?,
    @ColumnInfo(name = "status") val status: Int?
)
