package com.bakery.bakeryapp.data.sources

import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveToken(token: String)
    fun getToken(): Flow<String>
    suspend fun deleteToken()

    suspend fun saveUser(user: List<User>)
    fun getUser(): Flow<List<User>>
    suspend fun deleteUser()

    suspend fun saveCategories(categories: List<Category>)
    fun getCategories(): Flow<List<Category>>
    suspend fun deleteCategories()

    suspend fun saveProducts(products: List<Product>)
    fun getProducts(): Flow<List<ProductsWithCategories>>
    fun getProductDetail(id: String): Flow<List<ProductsWithCategories>>
    suspend fun deleteProducts()

    suspend fun saveCart(cart: List<Cart>)
    fun getCart(): Flow<List<Cart>>
    suspend fun deleteCart()

    suspend fun savePedidos(pedido: List<Pedido>)
    fun getPedido(): Flow<List<Pedido>>
    suspend fun deletePedido()
}
