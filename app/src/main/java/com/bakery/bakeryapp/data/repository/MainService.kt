package com.bakery.bakeryapp.data.repository

import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.common.toDomainAuth
import com.bakery.bakeryapp.common.toDomainCart
import com.bakery.bakeryapp.common.toDomainCategory
import com.bakery.bakeryapp.common.toDomainPedido
import com.bakery.bakeryapp.common.toDomainProduct
import com.bakery.bakeryapp.common.toServerLogin
import com.bakery.bakeryapp.common.toServerRegister
import com.bakery.bakeryapp.data.remote.api.Api
import com.bakery.bakeryapp.data.sources.RemoteDataSource
import com.bakery.bakeryapp.di.IoDispatcher
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.Auth
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.Register
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainService @Inject constructor(
    private val api: Api,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {
    override suspend fun login(login: Login): Flow<Resource<Auth>> = flow {
        emit(Resource.Loading())

        when (val response = api.login(login.toServerLogin())) {
            is NetworkResponse.Success -> {
                val loginResponse = response.body

                emit(Resource.Success(loginResponse.toDomainAuth()))
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun register(register: Register): Flow<Resource<Auth>> = flow {
        emit(Resource.Loading())

        when (val response = api.register(register.toServerRegister())) {
            is NetworkResponse.Success -> {
                val loginResponse = response.body

                emit(Resource.Success(loginResponse.toDomainAuth()))
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    /*override suspend fun getUser(accessToken: String, user: User): Flow<Resource<List<Auth>>> = flow {
        emit(Resource.Loading())

        when(val response = api.getUser())
    }.flowOn(ioDispatcher)*/

    override suspend fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())

        when (val response = api.getCategories()) {
            is NetworkResponse.Success -> {
                val categoryResponse = response.body
                emit(
                    Resource.Success(
                        categoryResponse.map { categoryResponseItem -> categoryResponseItem.toDomainCategory() }
                    )
                )
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())

        when (val response = api.getProducts()) {
            is NetworkResponse.Success -> {
                val productResponse = response.body
                emit(
                    Resource.Success(
                        productResponse.map { productResponseItem -> productResponseItem.toDomainProduct() }
                    )
                )
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun getCart(
        // accessToken: String,
        userId: String,
    ): Flow<Resource<List<Cart>>> = flow {
        emit(Resource.Loading())
        when (val response = api.getCart(userId)) {
            is NetworkResponse.Success -> {
                val cartResponse = response.body
                emit(
                    Resource.Success(
                        cartResponse.map { cartResponseItem -> cartResponseItem.toDomainCart() }
                    )
                )
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun getPedidos(): Flow<Resource<List<Pedido>>> = flow {
        emit(Resource.Loading())

        when (val response = api.getPedidos()) {
            is NetworkResponse.Success -> {
                val pedidosResponse = response.body
                emit(
                    Resource.Success(
                        pedidosResponse.map { pedidosResponseItem -> pedidosResponseItem.toDomainPedido() }
                    )
                )
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun updateCart(cart: Cart, accessToken: String): Flow<Resource<List<Cart>>> = flow {
        emit(Resource.Loading())

        when (val response = api.updateCart(cart, accessToken)) {
            is NetworkResponse.Success -> {
                val cartResponse = response.body
                emit(Resource.Success(cartResponse.map { cartResponseItem -> cartResponseItem.toDomainCart() }))
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)

    override suspend fun createPedido(
        pedido: Pedido,
        accessToken: String
    ): Flow<Resource<List<Pedido>>> = flow {
        emit(Resource.Loading())

        when (val response = api.createPedido(pedido, accessToken)) {
            is NetworkResponse.Success -> {
                val pedidoResponse = response.body
                emit(Resource.Success(pedidoResponse.map { pedidoResponseItem -> pedidoResponseItem.toDomainPedido() }))
            }

            is NetworkResponse.ServerError -> {
                emit(Resource.Error(message = response.body?.message ?: "Intentelo más tarde"))
            }

            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(message = "Compruebe su conexión a internet"))
            }

            is NetworkResponse.UnknownError -> {
                emit(Resource.Error(message = "Error desconocido, vuelva a intentarlo."))
            }
        }
    }.flowOn(ioDispatcher)
}
