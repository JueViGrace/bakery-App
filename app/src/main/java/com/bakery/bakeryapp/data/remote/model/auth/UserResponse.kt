package com.bakery.bakeryapp.data.remote.model.auth

data class UserResponse(
    val access_token: String,
    val user: UserCloud
)