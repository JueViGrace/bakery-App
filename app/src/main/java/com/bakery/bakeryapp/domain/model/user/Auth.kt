package com.bakery.bakeryapp.domain.model.user

data class Auth(
    val access_token: String,
    val user: User
)
