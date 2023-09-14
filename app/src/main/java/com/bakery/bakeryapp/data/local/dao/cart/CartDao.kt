package com.bakery.bakeryapp.data.local.dao.cart

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Upsert
    suspend fun upsertCart(cart: List<CartEntity>)

    @Query("DELETE FROM cart")
    suspend fun deleteCart()

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartEntity>>
}
