package com.mercadolibre.cristhianbonilla.support.config

import java.text.NumberFormat
import java.util.Locale

val Int.Companion.ZERO: Int
    get() = 0

val Double.Companion.ZERO: Double
    get() = 0.0

fun Int?.orDefault(default: Int = Int.ZERO) = this ?: default

fun Double?.orDefault(default: Double = Int.ZERO.toDouble()) = this ?: default

fun Double?.toCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale.US).format(this)
}