package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.support.config.DispatcherProvider
import com.mercadolibre.cristhianbonilla.support.config.DispatcherProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProviderModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()
}