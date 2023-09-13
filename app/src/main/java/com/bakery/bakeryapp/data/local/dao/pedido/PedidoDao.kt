package com.bakery.bakeryapp.data.local.dao.pedido

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Upsert
    suspend fun upsertPedido(pedido: List<PedidoEntity>)

    @Query("DELETE FROM pedidos")
    suspend fun deletePedidos()

    @Query("SELECT * FROM pedidos")
    fun getPedidos(): Flow<List<PedidoEntity>>
}
