package com.bakery.bakeryapp.data.local.dao.users

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity
import com.bakery.bakeryapp.data.local.entities.users.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: List<UserEntity>)

    @Query("DELETE FROM user")
    suspend fun deleteUser()

    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<UserEntity>>
}