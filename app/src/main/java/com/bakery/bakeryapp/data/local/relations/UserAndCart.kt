package com.bakery.bakeryapp.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity
import com.bakery.bakeryapp.data.local.entities.users.UserEntity

data class UserAndCart(
    @Embedded val user: UserEntity,
    @Relation(parentColumn = "_id", entityColumn = "_id")
    val cart: CartEntity
)
