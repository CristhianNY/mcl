package com.mercadolibre.cristhianbonilla.data.products.data_source

import com.mercadolibre.cristhianbonilla.data.entity.ProductEntity

class ProductDataSourceImpl : ProductDataSource {
    override suspend fun getProductsByName(name: String): List<ProductEntity> {
        TODO("Not yet implemented")
    }
}