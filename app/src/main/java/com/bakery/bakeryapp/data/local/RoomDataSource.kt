package com.bakery.bakeryapp.data.local

import com.bakery.bakeryapp.data.local.dao.cart.CartDao
import com.bakery.bakeryapp.data.local.dao.categories.CategoriesDao
import com.bakery.bakeryapp.data.local.dao.pedido.PedidoDao
import com.bakery.bakeryapp.data.local.dao.product.ProductDao
import com.bakery.bakeryapp.data.local.dao.users.UserDao
import javax.inject.Inject

class RoomDataSource @Inject constructor(
    private val userDao: UserDao,
    private val productDao: ProductDao,
    private val pedidoDao: PedidoDao,
    private val categoriesDao: CategoriesDao,
    private val cartDao: CartDao
): LocalDataSource {


}