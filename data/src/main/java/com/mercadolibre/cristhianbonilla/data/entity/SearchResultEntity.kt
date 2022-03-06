package com.mercadolibre.cristhianbonilla.data.entity

import com.google.gson.annotations.SerializedName

data class SearchResultEntity(
    @SerializedName("results")
    val products: List<ProductEntity>
)
