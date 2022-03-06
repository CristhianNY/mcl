package com.mercadolibre.cristhianbonilla.data.entity

import com.google.gson.annotations.SerializedName
import com.mercadolibre.cristhianbonilla.domain.model.SearchResultModel

data class SearchResultEntity(
    @SerializedName("results")
    val products: List<ProductEntity>?
)

fun SearchResultEntity.toModel() = SearchResultModel(products?.map { it.toModel() }.orEmpty())