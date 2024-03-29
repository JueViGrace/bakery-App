package com.bakery.bakeryapp.data.repository.datastore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.constantes.Constantes.ACCESS_TOKEN
import com.bakery.bakeryapp.constantes.Constantes.COD_USUARIO
import com.bakery.bakeryapp.constantes.Constantes.NOMBRE_USUARIO
import com.bakery.bakeryapp.data.repository.datastore.repository.DataStoreRepository
import com.bakery.bakeryapp.data.repository.datastore.state.DataStoreState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {

    val state = mutableStateOf(DataStoreState())

    val loginInProgress = mutableStateOf(false)

    fun storePreference(key: String, value: String) = viewModelScope.launch {
        dataStoreRepository.savePreference(key, value)
    }

    fun getAccessToken() = viewModelScope.launch {
        loginInProgress.value = true
        val accessToken = dataStoreRepository.getPreference(ACCESS_TOKEN)!!
        state.value = state.value.copy(
            accessToken = accessToken
        )
    }

    fun getCodigoUsuario() = viewModelScope.launch {
        var codUser = dataStoreRepository.getPreference(COD_USUARIO)
        if (codUser == null) {
            codUser = ""
        }
        state.value = state.value.copy(
            codigo = codUser
        )
    }

    fun getNombre() = viewModelScope.launch {
        val nombre = dataStoreRepository.getPreference(NOMBRE_USUARIO)!!
        state.value = state.value.copy(
            nombre = nombre
        )
    }

    fun clearPreferences(key: String) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.clearPreferences(key)
    }
}
