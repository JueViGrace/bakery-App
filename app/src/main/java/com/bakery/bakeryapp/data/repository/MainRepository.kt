package com.bakery.bakeryapp.data.repository

import com.bakery.bakeryapp.data.local.LocalDataSource
import com.bakery.bakeryapp.data.remote.RemoteDataSource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
){

}