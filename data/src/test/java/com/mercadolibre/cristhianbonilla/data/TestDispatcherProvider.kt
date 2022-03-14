package com.mercadolibre.cristhianbonilla.data

import com.mercadolibre.cristhianbonilla.support.config.DispatcherProvider
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProvider {
    override val main get() = Dispatchers.Main
    override val network get() = Dispatchers.Main
}
