package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.data.products.repository.ProductRepositoryImpl
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideProductRepository(impl: ProductRepositoryImpl): ProductRepository
}