package com.mercadolibre.cristhianbonilla.support.config

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val main: CoroutineDispatcher

    val network: CoroutineDispatcher
}