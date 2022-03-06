package com.mercadolibre.cristhianbonilla.support.config

val String.Companion.EMPTY: String
    get() = ""

fun String?.empty(empty: String = String.EMPTY) = this ?: empty