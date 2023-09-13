package com.bakery.bakeryapp.data.remote.api

import com.bakery.bakeryapp.data.remote.model.auth.AuthResponse
import com.bakery.bakeryapp.data.remote.model.auth.LoginResponse
import com.bakery.bakeryapp.data.remote.model.auth.RegisterResponse
import com.bakery.bakeryapp.data.remote.model.cart.CartResponse
import com.bakery.bakeryapp.data.remote.model.categories.CategoryResponse
import com.bakery.bakeryapp.data.remote.model.error.ErrorResponse
import com.bakery.bakeryapp.data.remote.model.pedido.PedidoResponse
import com.bakery.bakeryapp.data.remote.model.product.ProductResponse
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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
    suspend fun getProducts(): NetworkResponse<ProductResponse, ErrorResponse>

    @GET("category")
    suspend fun getCategories(): NetworkResponse<CategoryResponse, ErrorResponse>

    @GET("cart/{id}")
    suspend fun getCart(
        // @Header("jwt") apiKey: String,
        @Path("id") userId: String,
        // @Query("products") products: Array<String>
        // @Query("cartDto") cart: GetCart
    ): NetworkResponse<CartResponse, ErrorResponse>

    @GET("pedido/{id}")
    suspend fun getPedidos(
        @Path("id") userId: String
    ): NetworkResponse<PedidoResponse, ErrorResponse>

    @PUT("cart/update")
    suspend fun updateCart(
        @Body cart: Cart,
        @Query("access_token") apiKey: String,
    ): NetworkResponse<CartResponse, ErrorResponse>

    @POST("pedido/create")
    suspend fun createPedido(
        @Body pedido: Pedido,
        @Query("access_token") apiKey: String,
    ): NetworkResponse<PedidoResponse, ErrorResponse>
}
