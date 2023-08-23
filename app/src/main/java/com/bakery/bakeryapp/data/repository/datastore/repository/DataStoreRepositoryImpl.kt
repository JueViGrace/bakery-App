package com.bakery.bakeryapp.data.repository.datastore.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bakery.bakeryapp.constantes.Constantes.DATASTORE_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreRepositoryImpl @Inject constructor(
    private val context: Context
) : DataStoreRepository {

    override suspend fun savePreference(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getPreference(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        } catch (e: Throwable) {
            Log.e(e.localizedMessage, "Ha ocurrido un error ${e.message}", e.cause)
            null
        }
    }

    override suspend fun clearPreferences(key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            if (it.contains(preferenceKey)) {
                it.remove(preferenceKey)
            }
        }
    }
}
