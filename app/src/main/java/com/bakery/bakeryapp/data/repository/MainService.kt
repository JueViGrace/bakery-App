package com.bakery.bakeryapp.data.repository

import com.bakery.bakeryapp.data.remote.Api
import com.bakery.bakeryapp.data.remote.RemoteDataSource
import com.bakery.bakeryapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MainService @Inject constructor(
    private val api: Api,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemoteDataSource {



}