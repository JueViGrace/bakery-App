package com.bakery.bakeryapp.data.repository

import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.local.LocalDataSource
import com.bakery.bakeryapp.data.remote.RemoteDataSource
import com.bakery.bakeryapp.domain.model.user.Auth
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
){

    suspend fun login(
        login: Login
    ): Flow<Resource<Login>> = remoteDataSource.login(login)

    suspend fun saveUser(user: List<User>) = localDataSource.saveUser(user)

}