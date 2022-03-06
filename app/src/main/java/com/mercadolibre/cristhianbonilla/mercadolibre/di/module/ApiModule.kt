package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import com.mercadolibre.cristhianbonilla.data.products.api.ProductApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}