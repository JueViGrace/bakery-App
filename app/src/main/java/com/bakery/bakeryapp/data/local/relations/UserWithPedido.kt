package com.bakery.bakeryapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity
import com.bakery.bakeryapp.data.local.entities.users.UserEntity

data class UserWithPedido(
    @Embedded val user: UserEntity,
    @Relation(parentColumn = "_id", entityColumn = "user")
    val pedido: List<PedidoEntity>
)
