package com.mercadolibre.cristhianbonilla.support.config

val Int.Companion.ZERO: Int
    get() = 0

val Double.Companion.ZERO: Double
    get() = 0.0

fun Int?.orDefault(default: Int = Int.ZERO) = this ?: default

fun Double?.orDefault(default: Double = Int.ZERO.toDouble()) = this ?: default