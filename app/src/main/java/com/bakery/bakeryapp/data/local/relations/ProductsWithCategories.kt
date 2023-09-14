package com.bakery.bakeryapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity

data class ProductsWithCategories(
    @Embedded val products: ProductEntity,
    @Relation(parentColumn = "categories", entityColumn = "_id")
    val categories: List<CategoryEntity>
)
