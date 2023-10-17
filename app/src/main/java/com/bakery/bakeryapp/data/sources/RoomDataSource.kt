package com.bakery.bakeryapp.data.sources

import com.bakery.bakeryapp.common.toDomainCart
import com.bakery.bakeryapp.common.toDomainCategory
import com.bakery.bakeryapp.common.toDomainPedido
import com.bakery.bakeryapp.common.toDomainUser
import com.bakery.bakeryapp.common.toRoomCart
import com.bakery.bakeryapp.common.toRoomCategory
import com.bakery.bakeryapp.common.toRoomPedido
import com.bakery.bakeryapp.common.toRoomProduct
import com.bakery.bakeryapp.common.toRoomUser
import com.bakery.bakeryapp.data.local.dao.auth.AuthDao
import com.bakery.bakeryapp.data.local.dao.cart.CartDao
import com.bakery.bakeryapp.data.local.dao.categories.CategoriesDao
import com.bakery.bakeryapp.data.local.dao.pedido.PedidoDao
import com.bakery.bakeryapp.data.local.dao.product.ProductDao
import com.bakery.bakeryapp.data.local.dao.relations.RelationsDao
import com.bakery.bakeryapp.data.local.dao.users.UserDao
import com.bakery.bakeryapp.data.local.entities.auth.AuthEntity
import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val userDao: UserDao,
    private val productDao: ProductDao,
    private val pedidoDao: PedidoDao,
    private val categoriesDao: CategoriesDao,
    private val cartDao: CartDao,
    private val relationsDao: RelationsDao,
    private val authDao: AuthDao
) : LocalDataSource {

    override suspend fun saveToken(token: String) = authDao.upsertAuth(AuthEntity(accessToken = token))

    override fun getToken(): Flow<String> = authDao.getToken().map { value -> value.accessToken }

    override suspend fun deleteToken() = authDao.deleteAuth()

    override suspend fun saveUser(user: List<User>) =
        userDao.upsertUser(user.map { value -> value.toRoomUser() })

    override fun getUser(): Flow<List<User>> =
        userDao.getUser().map { value -> value.map { userEntity -> userEntity.toDomainUser() } }

    override suspend fun deleteUser() = userDao.deleteUser()

    override suspend fun saveCategories(categories: List<Category>) =
        categoriesDao.upsertCategories(categories.map { value -> value.toRoomCategory() })

    override fun getCategories(): Flow<List<Category>> = categoriesDao.getCategories()
        .map { value -> value.map { categoryEntity -> categoryEntity.toDomainCategory() } }

    override suspend fun deleteCategories() = categoriesDao.deleteCategories()

    override suspend fun saveProducts(products: List<Product>) =
        productDao.upsertProduct(products.map { product: Product -> product.toRoomProduct() })

    /*override fun getProducts(): Flow<List<Product>> = productDao.getProducts()
        .map { value -> value.map { productEntity -> productEntity.toDomainProduct() } }*/

    override fun getProducts(): Flow<List<ProductsWithCategories>> = relationsDao.getProductWithCategories()
        .map { value -> value.map { productsWithCategories -> productsWithCategories } }

    override suspend fun deleteProducts() = productDao.deleteProducts()

    override suspend fun saveCart(cart: List<Cart>) =
        cartDao.upsertCart(cart.map { value -> value.toRoomCart() })

    override fun getCart(): Flow<List<Cart>> =
        cartDao.getCart().map { value -> value.map { cartEntity -> cartEntity.toDomainCart() } }

    override suspend fun deleteCart() = cartDao.deleteCart()

    override suspend fun savePedidos(pedido: List<Pedido>) =
        pedidoDao.upsertPedido(pedido.map { value -> value.toRoomPedido() })

    override fun getPedido(): Flow<List<Pedido>> = pedidoDao.getPedidos()
        .map { value -> value.map { pedidoEntity -> pedidoEntity.toDomainPedido() } }

    override suspend fun deletePedido() = pedidoDao.deletePedidos()
}
