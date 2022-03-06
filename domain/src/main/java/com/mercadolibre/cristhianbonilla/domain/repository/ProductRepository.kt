package com.mercadolibre.cristhianbonilla.domain.repository

import com.mercadolibre.cristhianbonilla.domain.model.SearchResultModel
import com.mercadolibre.cristhianbonilla.support.config.ResultDomain

interface ProductRepository {
    suspend fun getProductsByName(name: String): ResultDomain<SearchResultModel>
}