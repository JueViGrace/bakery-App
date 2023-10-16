package com.bakery.bakeryapp.domain.model.user

data class User(
    val _id: String,
    val birthDate: String,
    val createdAt: String,
    val email: String,
    val fullName: String,
    val lastName: String,
    val name: String,
    val phone: String,
    val role: String
)
