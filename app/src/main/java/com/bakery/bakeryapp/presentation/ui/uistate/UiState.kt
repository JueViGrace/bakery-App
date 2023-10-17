package com.bakery.bakeryapp.presentation.ui.uistate

sealed interface UiState {
    data object Loading : UiState
    data class Error(val throwable: Throwable) : UiState
    data class Success<T>(val success: T) : UiState
}
