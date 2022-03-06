package com.mercadolibre.cristhianbonilla.data.products.data_source

import com.mercadolibre.cristhianbonilla.data.entity.ProductEntity

interface ProductDataSource {
    suspend fun getProductsByName(name: String): List<ProductEntity>
}