package com.bakery.bakeryapp.constantes

import com.google.android.play.core.ktx.BuildConfig
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Constantes {
    const val version: String = BuildConfig.VERSION_NAME
    const val build: String = BuildConfig.BUILD_TYPE
    const val BASE_URL = "https://5360-45-186-203-166.ngrok-free.app/api/"
    const val UPDATE_APP_REQUEST_CODE: Int = 200
    const val DATABASE_VERSION_OLD: Int = 0
    const val DATABASE_VERSION_NEW: Int = 2
    const val DATABASE_NAME: String = "bakery_db"
    const val DATASTORE_NAME = "PREFERECES"
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    // variables para dar formato a los numeros
    val formato = DecimalFormat("#,##0.00")
    val formato2 = DecimalFormat("#,##0")
    val ACCESS_TOKEN = "ACCESS_TOKEN_KEY"
    val COD_USUARIO = "COD_USUARIO_KEY"
    val NICK_USUARIO = "NICK_USUARIO_KEY"
    val NOMBRE_USUARIO = "NOMBRE_USUARIO_KEY"
    val CLEAR_PREFERENCES = "CLEAR_PREFERENCES_KEY"
    // fun LocalDateTime.toMillis() = this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
