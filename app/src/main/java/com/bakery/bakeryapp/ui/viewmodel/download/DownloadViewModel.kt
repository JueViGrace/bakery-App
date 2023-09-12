package com.bakery.bakeryapp.ui.viewmodel.download

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.di.NetworkModule
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
            addToken(state.value.accessToken)
            when (state.value.down) {
                0 -> {
                    getCategories()
                }
                1 -> {
                    getProducts()
                }
                2 -> {
                    getCart()
                }
                3 -> {
                    getPedidos()
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

    private fun getCart() {
        viewModelScope.launch {
            repository.getCart(state.value.userId).collect {
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

    private fun addToken(accessToken: String?) {
        NetworkModule.accessToken = accessToken
    }

    private fun getPedidos() {
        viewModelScope.launch {
            repository.getPedidos().collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data != null) {
                            repository.savePedidos(it.data)
                            state.value.down = 0
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
