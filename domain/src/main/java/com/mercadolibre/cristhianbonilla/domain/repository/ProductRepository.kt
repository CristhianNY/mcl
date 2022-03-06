package com.mercadolibre.cristhianbonilla.domain.repository

import com.mercadolibre.cristhianbonilla.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductsByName(name: String): List<ProductModel>
}