package com.mercadolibre.cristhianbonilla.data.entity

import com.google.gson.annotations.SerializedName
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.support.config.orDefault

data class ProductEntity(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean?,
    @SerializedName("available_quantity")
    val availableQuantity: Int?,
    @SerializedName("currency_id")
    val currencyId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("thumbnail_id")
    val thumbnailId: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean?
)

fun ProductEntity.toModel() = ProductModel(
    acceptsMercadopago.orDefault(),
    availableQuantity.orDefault(),
    currencyId.orEmpty(),
    id.orEmpty(),
    price.orDefault(),
    thumbnail.orEmpty(),
    thumbnailId.orEmpty(),
    title.orEmpty(),
    useThumbnailId.orDefault()
)