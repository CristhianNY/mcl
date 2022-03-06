package com.mercadolibre.cristhianbonilla.support.config

val Boolean.Companion.DEFAULT: Boolean
    get() = false

fun Boolean?.orDefault(default: Boolean = Boolean.DEFAULT) = this ?: default
