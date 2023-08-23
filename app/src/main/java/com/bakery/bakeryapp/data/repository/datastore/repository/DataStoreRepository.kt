package com.bakery.bakeryapp.data.repository.datastore.repository

interface DataStoreRepository {
    suspend fun savePreference(key: String, value: String)
    suspend fun getPreference(key: String): String?
    suspend fun clearPreferences(key: String)
}
