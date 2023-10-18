package com.bakery.bakeryapp.common

import androidx.compose.ui.Modifier
import com.bakery.bakeryapp.domain.model.cart.Cart
import com.bakery.bakeryapp.domain.model.category.Category
import com.bakery.bakeryapp.domain.model.pedido.Pedido
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.domain.model.user.Auth
import com.bakery.bakeryapp.domain.model.user.Login
import com.bakery.bakeryapp.domain.model.user.Register
import com.bakery.bakeryapp.domain.model.user.User
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.bakery.bakeryapp.data.local.entities.cart.CartEntity as RoomCart
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity as RoomCategory
import com.bakery.bakeryapp.data.local.entities.pedido.PedidoEntity as RoomPedido
import com.bakery.bakeryapp.data.local.entities.product.ProductEntity as RoomProduct
import com.bakery.bakeryapp.data.local.entities.users.UserEntity as RoomUser
import com.bakery.bakeryapp.data.remote.model.auth.AuthResponse as ServerAuth
import com.bakery.bakeryapp.data.remote.model.auth.LoginResponse as ServerLogin
import com.bakery.bakeryapp.data.remote.model.auth.RegisterResponse as ServerRegister
import com.bakery.bakeryapp.data.remote.model.auth.UserCloud as ServerUser
import com.bakery.bakeryapp.data.remote.model.cart.CartResponseItem as ServerCart
import com.bakery.bakeryapp.data.remote.model.categories.CategoryResponseItem as ServerCategory
import com.bakery.bakeryapp.data.remote.model.pedido.PedidoResponseItem as ServerPedido
import com.bakery.bakeryapp.data.remote.model.product.ProductResponseItem as ServerProduct

inline fun Modifier.thenIf(predicate: Boolean, modify: () -> Modifier): Modifier {
    return this.then(if (predicate) modify() else Modifier)
}

fun Date.toCustomFormat(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this)
}

fun ServerLogin.toDomainLogin() = Login(
    email,
    password
)

fun Login.toServerLogin() = ServerLogin(
    email,
    password
)

fun ServerRegister.toDomainRegister() = Register(
    birthDate, createdAt, email, fullName, lastName, name, password, phone, role
)

fun Register.toServerRegister() = ServerRegister(
    birthDate, createdAt, email, fullName, lastName, name, password, phone, role
)

fun ServerAuth.toDomainAuth() = Auth(
    access_token,
    user.toDomainUser()
)

fun Auth.toServerAuth() = ServerAuth(
    accessToken,
    user.toServerUser()
)

fun ServerUser.toDomainUser() = User(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun User.toRoomUser() = RoomUser(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun RoomUser.toDomainUser() = User(
    _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun User.toServerUser() = ServerUser(
    null, _id, birthDate, createdAt, email, fullName, lastName, name, phone, role
)

fun ServerCategory.toDomainCategory() = Category(
    _id,
    categoryImage,
    createdAt,
    name
)

fun Category.toRoomCategory() = RoomCategory(
    _id,
    categoryImage,
    createdAt,
    name
)

fun RoomCategory.toDomainCategory() = Category(
    _id,
    categoryImage,
    createdAt,
    name
)

fun ServerProduct.toDomainProduct() = Product(
    _id, categories, createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun Product.toRoomProduct() = RoomProduct(
    _id, categories[0], createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun Product.toServerProduct() = ServerProduct(
    null, _id, categories, createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun RoomProduct.toDomainProduct() = Product(
    _id, listOf(categories), createdAt, haveStock, name, price, productImage, stock, truePrice
)

fun ServerCart.toDomainCart() = Cart(
    _id,
    createdAt,
    products,
    status
)

fun Cart.toRoomCart() = RoomCart(
    _id,
    createdAt,
    products,
    status
)

fun RoomCart.toDomainCart() = Cart(
    _id,
    createdAt,
    products,
    status
)

fun ServerPedido.toDomainPedido() = Pedido(
    _id,
    cart._id,
    createdAt,
    status,
    user._id
)

fun Pedido.toRoomPedido() = RoomPedido(
    _id,
    cart,
    createdAt,
    status,
    user
)

fun RoomPedido.toDomainPedido() = Pedido(
    _id,
    cart,
    createdAt,
    status,
    user
)
