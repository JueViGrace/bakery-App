package com.bakery.bakeryapp.data.remote

import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.remote.model.auth.LoginResponse
import com.bakery.bakeryapp.data.remote.model.auth.RegisterResponse
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.Auth
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun login(
        login: Login
    ): Flow<Resource<Login>>

    fun register(
        register: Register
    ): Flow<Resource<Register>>

    fun getUser(
        accessToken: String,
        user: User
    ): Flow<Resource<List<Auth>>>

    fun getCategories(
        accessToken: String
    ): Flow<Resource<List<Category>>>

    fun getProducts(
        accessToken: String
    ): Flow<Resource<List<Product>>>

    fun getCart(
        accessToken: String
    ): Flow<Resource<List<Cart>>>

    fun getPedidos(
        accessToken: String
    ): Flow<Resource<List<Pedido>>>
}