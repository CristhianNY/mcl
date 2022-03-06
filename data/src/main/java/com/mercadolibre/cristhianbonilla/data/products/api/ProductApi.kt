package com.mercadolibre.cristhianbonilla.data.products.api

import com.mercadolibre.cristhianbonilla.data.entity.ProductEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("sites/MLA/")
    suspend fun getProductsByName(@Query("search") name: String): Response<List<ProductEntity>>
}