package com.bakery.bakeryapp.di

import android.content.Context
import androidx.room.Room
import com.bakery.bakeryapp.constantes.Constantes.DATABASE_NAME
import com.bakery.bakeryapp.data.sources.LocalDataSource
import com.bakery.bakeryapp.data.local.db.RoomDataBase
import com.bakery.bakeryapp.data.sources.RoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RoomDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideRoomDataSource(
        db: RoomDataBase
    ): LocalDataSource {
        return RoomDataSource(
            userDao = db.userDao(),
            productDao = db.productDao(),
            pedidoDao = db.pedidoDao(),
            categoriesDao = db.categoriesDao(),
            cartDao = db.cartDao()
        )
    }
}
