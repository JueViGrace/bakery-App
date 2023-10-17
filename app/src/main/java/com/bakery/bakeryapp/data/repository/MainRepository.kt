package com.bakery.bakeryapp.data.repository

import com.bakery.bakeryapp.data.sources.LocalDataSource
import com.bakery.bakeryapp.data.sources.RemoteDataSource
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.domain.model.user.User
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun upsertToken(token: String) = localDataSource.saveToken(token)
    fun getToken() = localDataSource.getToken()
    suspend fun deleteToken() = localDataSource.deleteToken()

    suspend fun login(login: Login) = remoteDataSource.login(login)

    suspend fun register(register: Register) = remoteDataSource.register(register)

    /*suspend fun getUser(
        accessToken: String, user: User
    ) = remoteDataSource.getUser(accessToken, user)*/

    suspend fun getCategories() = remoteDataSource.getCategories()

    suspend fun getProducts() = remoteDataSource.getProducts()

    suspend fun getCart(userId: String) = remoteDataSource.getCart(userId)

    suspend fun getPedidos(userId: String) = remoteDataSource.getPedidos(userId)

    suspend fun updateCart(cart: Cart, accessToken: String) = remoteDataSource.updateCart(cart, accessToken)

    suspend fun createPedido(pedido: Pedido, accessToken: String) = remoteDataSource.createPedido(pedido, accessToken)

    suspend fun saveUser(user: List<User>) = localDataSource.saveUser(user)

    fun getUser() = localDataSource.getUser()

    suspend fun deleteUser() = localDataSource.deleteUser()

    suspend fun saveCategories(categories: List<Category>) =
        localDataSource.saveCategories(categories)

    fun getCategoriesFromDB() = localDataSource.getCategories()

    suspend fun deleteCategories() = localDataSource.deleteCategories()

    suspend fun saveProducts(products: List<Product>) = localDataSource.saveProducts(products)

    fun getProductsFromDB() = localDataSource.getProducts()

    suspend fun deleteProducts() = localDataSource.deleteProducts()

    suspend fun saveCart(cart: List<Cart>) = localDataSource.saveCart(cart)

    fun getCart() = localDataSource.getCart()

    suspend fun deleteCart() = localDataSource.deleteCart()

    suspend fun savePedidos(pedido: List<Pedido>) = localDataSource.savePedidos(pedido)

    fun getPedido() = localDataSource.getPedido()

    suspend fun deletePedido() = localDataSource.deletePedido()
}
