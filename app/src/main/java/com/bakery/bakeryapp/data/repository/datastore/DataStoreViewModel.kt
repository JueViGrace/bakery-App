package com.bakery.bakeryapp.data.repository.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.constantes.Constantes.COD_USUARIO
import com.bakery.bakeryapp.constantes.Constantes.NICK_USUARIO
import com.bakery.bakeryapp.constantes.Constantes.NOMBRE_USUARIO
import com.bakery.bakeryapp.data.repository.datastore.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    fun storePreference(key: String, value: String) = runBlocking(Dispatchers.IO) {
        dataStoreRepository.savePreference(key, value)
    }

    fun getCodigoUsuario(): String = runBlocking {
        dataStoreRepository.getPreference(COD_USUARIO)!!
    }

    fun getNombre(): String = runBlocking {
        dataStoreRepository.getPreference(NOMBRE_USUARIO)!!
    }

    fun getNickUsuario(): String = runBlocking {
        dataStoreRepository.getPreference(NICK_USUARIO)!!
    }

    fun clearPreferences(key: String) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.clearPreferences(key)
    }
}
