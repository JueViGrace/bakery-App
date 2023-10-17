package com.bakery.bakeryapp.presentation.download

import androidx.lifecycle.ViewModel
import com.bakery.bakeryapp.data.repository.MainRepository
import com.bakery.bakeryapp.di.NetworkModule
import com.bakery.bakeryapp.domain.usecase.DeleteDataBaseUseCase
import com.bakery.bakeryapp.presentation.ui.uistate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val repository: MainRepository,
    private val deleteDataBaseUseCase: DeleteDataBaseUseCase,
) : ViewModel() {

    /*val state = mutableStateOf(DownloadState())

    val loadingInProgress = mutableStateOf(true)

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    var loginInProgress = mutableStateOf(true)*/

    private val _state = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = _state

    fun onDownload() {
//        addToken(state.value.accessToken)
    }

    private fun addToken(accessToken: String?) {
        NetworkModule.accessToken = accessToken
    }



    /*private fun getCategories() {
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
    }*/
}
