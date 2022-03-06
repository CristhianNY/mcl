package com.mercadolibre.cristhianbonilla.data.products.data_source

import com.mercadolibre.cristhianbonilla.data.BaseDataSource
import com.mercadolibre.cristhianbonilla.data.entity.SearchResultEntity
import com.mercadolibre.cristhianbonilla.data.products.api.ProductApi
import com.mercadolibre.cristhianbonilla.support.config.Result
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(private val productsApi: ProductApi) :
    ProductDataSource, BaseDataSource() {
    override suspend fun getProductsByName(name: String): Result<SearchResultEntity> = getResult {
        executeNetworkAction {
            productsApi.getProductsByName(name)
        }
    }
}