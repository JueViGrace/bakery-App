package com.bakery.bakeryapp.data.local.dao.product

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsertProduct(product: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteProducts()

    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<ProductEntity>>
}
