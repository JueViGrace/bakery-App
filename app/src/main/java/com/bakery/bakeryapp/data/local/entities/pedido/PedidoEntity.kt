package com.bakery.bakeryapp.data.local.entities.pedido

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import com.bakery.bakeryapp.data.local.entities.users.UserEntity

@Entity(tableName = "pedidos")
data class PedidoEntity(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "cart") val cart: CartEntity,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "user") val user: UserEntity
)
