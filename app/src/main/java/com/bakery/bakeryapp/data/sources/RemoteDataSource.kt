package com.bakery.bakeryapp.data.sources

import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.Auth
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.Register
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun login(
        login: Login
    ): Flow<Resource<Auth>>

    suspend fun register(
        register: Register
    ): Flow<Resource<Auth>>

    /*suspend fun getUser(
        accessToken: String,
        user: User
    ): Flow<Resource<List<Auth>>>*/

    suspend fun getCategories(): Flow<Resource<List<Category>>>

    suspend fun getProducts(): Flow<Resource<List<Product>>>

    suspend fun getCart(
        accessToken: String,
        userId: String,
        products: List<Product>
    ): Flow<Resource<List<Cart>>>

    suspend fun getPedidos(
        accessToken: String
    ): Flow<Resource<List<Pedido>>>

    suspend fun updateCart(
        cart: Cart,
        accessToken: String
    ): Flow<Resource<List<Cart>>>

    suspend fun createPedido(
        pedido: Pedido,
        accessToken: String
    ): Flow<Resource<List<Pedido>>>
}
