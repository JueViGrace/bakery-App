package com.bakery.bakeryapp.data.remote

import com.bakery.bakeryapp.data.remote.model.auth.LoginResponse
import com.bakery.bakeryapp.data.remote.model.auth.RegisterResponse
import com.bakery.bakeryapp.data.remote.model.auth.AuthResponse
import com.bakery.bakeryapp.data.remote.model.cart.CartResponse
import com.bakery.bakeryapp.data.remote.model.categories.CategoryResponse
import com.bakery.bakeryapp.data.remote.model.pedido.PedidoResponse
import com.bakery.bakeryapp.data.remote.model.product.ProductResponse
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface Api {

    @POST("auth/login")
    suspend fun login(
        @Body login: LoginResponse
    ): NetworkResponse<AuthResponse, ErrorResponse>

    @POST("auth/register")
    suspend fun register(
        @Body register: RegisterResponse
    ): NetworkResponse<AuthResponse, ErrorResponse>

    @GET("product/all")
    suspend fun getProducts(
        @Query("access_token") apiKey: String,
    ): NetworkResponse<ProductResponse, ErrorResponse>

    @GET("category")
    suspend fun getCategories(
        @Query("access_token") apiKey: String,
    ): NetworkResponse<CategoryResponse, ErrorResponse>

    @GET("cart")
    suspend fun getCart(
        @Query("cart") cart: Cart
    ): NetworkResponse<CartResponse, ErrorResponse>

    @GET("pedido")
    suspend fun getPedidos(
        @Query("access_token") apiKey: String,
    ): NetworkResponse<PedidoResponse, ErrorResponse>

    @PUT("cart/update")
    suspend fun updateCart(
        @Body cart: Cart
    ): NetworkResponse<CartResponse, ErrorResponse>

    @POST("pedido/create")
    suspend fun createPedido(
        @Body pedido: Pedido
    ): NetworkResponse<PedidoResponse, ErrorResponse>
}