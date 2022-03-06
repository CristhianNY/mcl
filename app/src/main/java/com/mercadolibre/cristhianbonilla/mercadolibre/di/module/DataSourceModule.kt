package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSource
import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun provideProductDataSource(
        impl: ProductDataSourceImpl
    ): ProductDataSource

}