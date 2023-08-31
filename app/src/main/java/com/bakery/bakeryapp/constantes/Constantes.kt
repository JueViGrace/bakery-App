package com.bakery.bakeryapp.constantes

import com.google.android.play.core.ktx.BuildConfig
import java.text.DecimalFormat

object Constantes {
    const val version: String = BuildConfig.VERSION_NAME
    const val build: String = BuildConfig.BUILD_TYPE
    const val BASE_URL = "https://localhost:3000/api/"
    const val UPDATE_APP_REQUEST_CODE: Int = 200
    const val DATABASE_VERSION_OLD: Int = 0
    const val DATABASE_VERSION_NEW: Int = 1
    const val DATABASE_NAME: String = "bakery_db"
    const val DATASTORE_NAME = "PREFERECES"

    // variables para dar formato a los numeros
    val formato = DecimalFormat("#,##0.00")
    val formato2 = DecimalFormat("#,##0")
    val COD_USUARIO = "COD_USUARIO_KEY"
    val NICK_USUARIO = "NICK_USUARIO_KEY"
    val NOMBRE_USUARIO = "NOMBRE_USUARIO_KEY"
    val CLEAR_PREFERENCES = "CLEAR_PREFERENCES_KEY"
}
