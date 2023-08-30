package com.bakery.bakeryapp.common

import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity as RoomPedido
import com.bakery.bakeryapp.data.remote.model.pedido.PedidoResponseItem as ServerPedido
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity as RoomCart
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.data.remote.model.cart.CartResponseItem as ServerCart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.User
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity as RoomCategory
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity as RoomProduct
import com.bakery.bakeryapp.data.local.entities.users.UserEntity as RoomUser
import com.bakery.bakeryapp.data.remote.model.auth.UserCloud as ServerUser
import com.bakery.bakeryapp.data.remote.model.categories.CategoryResponseItem as ServerCategory
import com.bakery.bakeryapp.data.remote.model.product.ProductResponseItem as ServerProduct

fun ServerUser.toDomainUser() = User(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun User.toRoomUser() = RoomUser(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun RoomUser.toDomainUser() = User(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun ServerCategory.toDomainCategory() = Category(
    _id, categoryImage, createdAt, name
)

fun Category.toRoomCategory() = RoomCategory(
    _id, categoryImage, createdAt, name
)

fun RoomCategory.toDomainCategory() = Category(
    _id, categoryImage, createdAt, name
)

fun ServerProduct.toDomainProduct() = Product(
    _id, categories, createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun Product.toRoomProduct() = RoomProduct(
    _id, categories, createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun RoomProduct.toDomainProduct() = Product(
    _id, categories, createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun ServerCart.toDomainCart() = Cart(
    _id, createdAt, products, status
)

fun Cart.toRoomCart() = RoomCart(
    _id, createdAt, products, status
)

fun RoomCart.toDomainCart() = Cart(
    _id, createdAt, products, status
)

fun ServerPedido.toDomainPedido(): Pedido {
    val cart = Cart(cart._id, cart.createdAt, cart.products, cart.status)
    val user = User(
        user._id,
        user.birthDate,
        user.createdAt,
        user.email,
        user.fullName,
        user.lastName,
        user.name,
        user.phone,
        user.role
    )
    return Pedido(_id, cart, createdAt, status, user)
}

fun Pedido.toRoomPedido() = RoomPedido(
    _id, cart.toRoomCart(), createdAt, status, user.toRoomUser()
)

fun RoomPedido.toDomainPedido() = Pedido(
    _id, cart.toDomainCart(), createdAt, status, user.toDomainUser()
)