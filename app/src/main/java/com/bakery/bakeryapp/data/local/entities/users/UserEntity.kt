package com.bakery.bakeryapp.data.local.entities.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "birthDate") val birthDate: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "fullName") val fullName: String,
    @ColumnInfo(name = "lastName") val lastName: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "role") val role: String
)
