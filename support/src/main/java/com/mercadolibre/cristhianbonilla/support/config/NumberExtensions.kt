package com.mercadolibre.cristhianbonilla.support.config

import java.text.NumberFormat
import java.util.Locale

val Int.Companion.ZERO: Int
    get() = 0

fun Double?.orDefault(default: Double = Int.ZERO.toDouble()) = this ?: default

fun Double?.toCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}