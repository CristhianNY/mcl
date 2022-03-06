package com.mercadolibre.cristhianbonilla.data.products.repository

import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository

class ProductRepositoryImpl : ProductRepository {

    override suspend fun getProductsByName(name: String): List<ProductModel> {
        TODO("Not yet implemented")
    }
}