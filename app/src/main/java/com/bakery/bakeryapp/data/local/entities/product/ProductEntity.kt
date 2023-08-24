package com.bakery.bakeryapp.data.local.entities.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "categories") val categories: List<CategoryEntity>,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "haveStock") val haveStock: Boolean,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "productImage") val productImage: String,
    @ColumnInfo(name = "stock") val stock: String,
    @ColumnInfo(name = "truePrice") val truePrice: String
)
