package com.bakery.bakeryapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bakery.bakeryapp.constantes.Constantes.DATABASE_VERSION_NEW
import com.bakery.bakeryapp.data.local.converters.Converters
import com.bakery.bakeryapp.data.local.dao.auth.AuthDao
import com.bakery.bakeryapp.data.local.dao.cart.CartDao
import com.bakery.bakeryapp.data.local.dao.categories.CategoriesDao
import com.bakery.bakeryapp.data.local.dao.pedido.PedidoDao
import com.bakery.bakeryapp.data.local.dao.product.ProductDao
import com.bakery.bakeryapp.data.local.dao.relations.RelationsDao
import com.bakery.bakeryapp.data.local.dao.users.UserDao
import com.bakery.bakeryapp.data.local.entities.auth.AuthEntity
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity
import com.bakery.bakeryapp.data.local.entities.users.UserEntity

@Database(
    entities = [
        CartEntity::class,
        CategoryEntity::class,
        PedidoEntity::class,
        ProductEntity::class,
        UserEntity::class,
        AuthEntity::class
    ],
    version = DATABASE_VERSION_NEW,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class RoomDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

    abstract fun pedidoDao(): PedidoDao

    abstract fun categoriesDao(): CategoriesDao

    abstract fun cartDao(): CartDao

    abstract fun relationsDao(): RelationsDao

    abstract fun authDao(): AuthDao
}
