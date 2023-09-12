package com.bakery.bakeryapp.di

import android.content.Context
import com.angel.clocordinadores.data.remote.connectivity.NetworkConnectivityObserver
import com.bakery.bakeryapp.data.remote.api.Api
import com.bakery.bakeryapp.data.remote.connectivity.ConnectivityObserver
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.data.repository.MainService
import com.bakery.bakeryapp.data.sources.LocalDataSource
import com.bakery.bakeryapp.data.sources.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideMainRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MainRepository {
        return MainRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Singleton
    @Provides
    fun provideMainService(
        api: Api,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): RemoteDataSource {
        return MainService(
            api = api,
            ioDispatcher = ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(
        context: Context
    ): ConnectivityObserver {
        return NetworkConnectivityObserver(
            context = context
        )
    }
}
