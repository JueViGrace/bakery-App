package com.bakery.bakeryapp.data.local.entities.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth")
data class AuthEntity(
    @PrimaryKey val accessToken: String
)
