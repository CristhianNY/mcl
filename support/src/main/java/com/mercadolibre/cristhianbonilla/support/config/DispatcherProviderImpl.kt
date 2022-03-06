package com.mercadolibre.cristhianbonilla.support.config

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override val main get() = Dispatchers.Main
    override val network get() = Dispatchers.IO
}