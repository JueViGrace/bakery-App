package com.bakery.bakeryapp.di

import android.content.Context
import com.bakery.bakeryapp.data.repository.datastore.repository.DataStoreRepository
import com.bakery.bakeryapp.data.repository.datastore.repository.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(
            context
        )
}
