package com.bakery.bakeryapp.ui.viewmodel.download

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.ui.states.download.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    val state = mutableStateOf(DownloadState())

    val loadingInProgress = mutableStateOf(true)

    fun onDownload() {
        viewModelScope.launch {
            when (state.value.down) {
                0 -> {
                    getCategories()
                }
                1 -> {
                    getProducts()
                }
                2 -> {
                    getCart(state.value.accessToken)
                }
                3 -> {
                    getPedidos(state.value.accessToken)
                }
                else -> {
                    loadingInProgress.value = false
                    state.value = state.value.copy(
                        down = 0
                    )
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            repository.getCategories().collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.saveCategories(it.data)
                            state.value.down++
                            onDownload()
                        } else {
                            loadingInProgress.value = false
                            state.value.loadingError = true
                        }
                    }

                    is Resource.Error -> {
                        loadingInProgress.value = false
                        state.value.loadingError = true
                        state.value = state.value.copy(
                            loadingMessage = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loadingInProgress.value = true
                    }
                }
            }
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            repository.getProducts().collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.saveProducts(it.data)
                            state.value.down++
                            onDownload()
                        } else {
                            loadingInProgress.value = false
                            state.value.loadingError = true
                        }
                    }

                    is Resource.Error -> {
                        loadingInProgress.value = false
                        state.value.loadingError = true
                        state.value = state.value.copy(
                            loadingMessage = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loadingInProgress.value = true
                    }
                }
            }
        }
    }

    private fun getCart(accessToken: String) {
        viewModelScope.launch {
            repository.getCart(accessToken, state.value.userId, state.value.products).collect {
                Log.d("downCArt", "getCart: $accessToken")
                Log.d("downCArt", "getCart: ${state.value.userId}")
                Log.d("downCArt", "getCart: ${state.value.products}")
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.saveCart(it.data)
                            // delay(2000)
                            state.value.down++
                            onDownload()
                        } else {
                            loadingInProgress.value = false
                            state.value.loadingError = true
                        }
                    }
                    is Resource.Error -> {
                        loadingInProgress.value = false
                        state.value.loadingError = true
                        state.value = state.value.copy(
                            loadingMessage = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loadingInProgress.value = true
                    }
                }
            }
        }
    }

    private fun getPedidos(accessToken: String) {
        viewModelScope.launch {
            repository.getPedidos(accessToken).collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.savePedidos(it.data)
                            state.value.down++
                            onDownload()
                        } else {
                            loadingInProgress.value = false
                            state.value.loadingError = true
                        }
                    }
                    is Resource.Error -> {
                        loadingInProgress.value = false
                        state.value.loadingError = true
                        state.value = state.value.copy(
                            loadingMessage = it.message
                        )
                    }

                    is Resource.Loading -> {
                        loadingInProgress.value = true
                    }
                }
            }
        }
    }
}
