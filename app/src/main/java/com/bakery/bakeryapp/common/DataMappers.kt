package com.bakery.bakeryapp.common

import com.bakery.bakeryapp.domain.model.user.User
import com.bakery.bakeryapp.data.remote.model.auth.UserCloud as serverUser

fun serverUser.toDomainUser() = User(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

