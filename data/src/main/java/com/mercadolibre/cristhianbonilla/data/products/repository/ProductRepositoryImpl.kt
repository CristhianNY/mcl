package com.mercadolibre.cristhianbonilla.data.products.repository

import com.mercadolibre.cristhianbonilla.data.entity.toModel
import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSource
import com.mercadolibre.cristhianbonilla.domain.model.SearchResultModel
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository
import com.mercadolibre.cristhianbonilla.support.config.GenericErrorMapper
import com.mercadolibre.cristhianbonilla.support.config.ResultDomain
import com.mercadolibre.cristhianbonilla.support.config.baseResponseErrorHandler
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val dataSource: ProductDataSource) :
    ProductRepository {
    override suspend fun getProductsByName(name: String): ResultDomain<SearchResultModel> =
        baseResponseErrorHandler(
            GenericErrorMapper,
            dataSource.getProductsByName(name)
        ) { ResultDomain.Success(it.toModel()) }

}