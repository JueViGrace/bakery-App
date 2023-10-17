package com.bakery.bakeryapp.data.local.dao.auth

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.auth.AuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {

    @Upsert
    suspend fun upsertAuth(auth: AuthEntity)

    @Query("DELETE FROM auth")
    suspend fun deleteAuth()

    @Query("SELECT * FROM auth")
    fun getToken(): Flow<AuthEntity>
}
