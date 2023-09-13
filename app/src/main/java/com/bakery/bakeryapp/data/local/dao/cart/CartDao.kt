package com.bakery.bakeryapp.data.local.dao.cart

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Transaction
    @Upsert
    suspend fun upsertCart(cart: List<CartEntity>)

    @Transaction
    @Query("DELETE FROM cart")
    suspend fun deleteCart()

    @Transaction
    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartEntity>>
}
