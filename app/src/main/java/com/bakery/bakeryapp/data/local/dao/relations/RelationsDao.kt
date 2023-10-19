package com.bakery.bakeryapp.data.local.dao.relations

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.bakery.bakeryapp.data.local.relations.CartWithPedido
import com.bakery.bakeryapp.data.local.relations.CartWithProducts
import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories
import com.bakery.bakeryapp.data.local.relations.UserAndCart
import com.bakery.bakeryapp.data.local.relations.UserWithPedido
import kotlinx.coroutines.flow.Flow

@Dao
interface RelationsDao {

    @Transaction
    @Query("SELECT * FROM user WHERE _id = :id")
    fun getUserAndCart(id: String): Flow<UserAndCart>

    @Transaction
    @Query("SELECT * FROM cart WHERE products = :id")
    fun getCartWithProducts(id: String): Flow<List<CartWithProducts>>

    @Transaction
    @Query("SELECT * FROM pedidos WHERE _id = :id")
    fun getPedidoWithCart(id: String): Flow<List<CartWithPedido>>

    @Transaction
    @Query("SELECT * FROM products")
    fun getProductWithCategories(): Flow<List<ProductsWithCategories>>

    @Transaction
    @Query("SELECT * FROM products WHERE _id = :id")
    fun getProductWithCategoriesById(id: String): Flow<List<ProductsWithCategories>>

    @Transaction
    @Query("SELECT * FROM user WHERE _id = :id")
    fun getUserWithPedido(id: String): Flow<List<UserWithPedido>>
}
