package com.bakery.bakeryapp.ui.viewmodel.download

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.di.NetworkModule
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import com.bakery.bakeryapp.ui.states.download.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase,
) : ViewModel() {

    val state = mutableStateOf(DownloadState())

    val loadingInProgress = mutableStateOf(true)

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    var loginInProgress = mutableStateOf(true)

    fun onDownload() {
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
                state.value.down = 0
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
            repository.getPedidos(state.value.userId).collect {
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

    fun checkForActiveSession() {
        viewModelScope.launch {
            val result = repository.getUser()

            result.collectLatest {
                if (it.isNotEmpty()) {
                    Log.d("downloadViewmodel", "Valid Session")
                    isUserLoggedIn.value = true
                } else {
                    Log.d("downloadViewmodel", "User is not logged in")
                    isUserLoggedIn.value = false
                    deleteDataBaseUseCase.invoke()
                }
            }
        }
    }
}
