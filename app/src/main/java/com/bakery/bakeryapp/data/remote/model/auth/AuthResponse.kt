package com.bakery.bakeryapp.data.remote.model.auth

data class AuthResponse(
    val access_token: String,
    val user: UserCloud
)
