package com.bakery.bakeryapp.domain.model.user

data class Auth(
    val accessToken: String,
    val user: User
)
