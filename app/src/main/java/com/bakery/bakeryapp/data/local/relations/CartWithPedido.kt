package com.bakery.bakeryapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity

data class CartWithPedido(
    @Embedded val cart: CartEntity,
    @Relation(parentColumn = "_id", entityColumn = "cart")
    val pedido: List<PedidoEntity>
)
