package com.bakery.bakeryapp.data.local.entities.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bakery.bakeryapp.data.local.converters.Converters

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val _id: String,
    @TypeConverters(Converters::class)
    // @ColumnInfo(name = "categories")
    val categories:String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "haveStock") val haveStock: Boolean,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "productImage") val productImage: String,
    @ColumnInfo(name = "stock") val stock: Int,
    @ColumnInfo(name = "truePrice") val truePrice: Int
)
