package com.mercadolibre.cristhianbonilla.data.products.data_source

import com.mercadolibre.cristhianbonilla.data.entity.SearchResultEntity
import com.mercadolibre.cristhianbonilla.support.config.Result

interface ProductDataSource {
    suspend fun getProductsByName(name: String): Result<SearchResultEntity>
}