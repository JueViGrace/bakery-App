package com.bakery.bakeryapp.data.remote.model.auth

data class RegisterResponse(
    val birthDate: String,
    val createdAt: String,
    val email: String,
    val fullName: String,
    val lastName: String,
    val name: String,
    val password: String,
    val phone: String,
    val role: String
)