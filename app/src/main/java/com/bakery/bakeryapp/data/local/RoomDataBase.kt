package com.bakery.bakeryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bakery.bakeryapp.constantes.Constantes.DATABASE_VERSION_NEW
import com.bakery.bakeryapp.data.local.dao.cart.CartDao
import com.bakery.bakeryapp.data.local.dao.categories.CategoriesDao
import com.bakery.bakeryapp.data.local.dao.pedido.PedidoDao
import com.bakery.bakeryapp.data.local.dao.product.ProductDao
import com.bakery.bakeryapp.data.local.dao.users.UserDao

@Database(
    entities = [

    ],
    version = DATABASE_VERSION_NEW,
    exportSchema = true
)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

    abstract fun pedidoDao(): PedidoDao

    abstract fun categoriesDao(): CategoriesDao

    abstract fun cartDao(): CartDao
}