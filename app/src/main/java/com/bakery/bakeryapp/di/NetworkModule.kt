package com.bakery.bakeryapp.di

import com.bakery.bakeryapp.constantes.Constantes.BASE_URL
import com.bakery.bakeryapp.data.remote.api.Api
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    var accessToken: String? = ""

    private fun loggingInterceptor(): OkHttpClient {
        return okHttpClient.addInterceptor(loggingInterceptor).build()
    }

    private fun sendToken(): OkHttpClient {
        okHttpClient.interceptors().clear()
        return okHttpClient.addInterceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
                .header("Authorization", "Bearer $accessToken")

            val request = builder.build()
            return@addInterceptor chain.proceed(request)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(sendToken())
            .client(loggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}
