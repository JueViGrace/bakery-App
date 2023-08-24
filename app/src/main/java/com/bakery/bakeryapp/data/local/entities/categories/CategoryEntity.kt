package com.bakery.bakeryapp.data.local.entities.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "categoryImage") val categoryImage: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "name") val name: String
)
