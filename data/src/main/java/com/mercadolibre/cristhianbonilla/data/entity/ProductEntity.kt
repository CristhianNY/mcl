package com.mercadolibre.cristhianbonilla.data.entity

import com.google.gson.annotations.SerializedName

data class ProductEntity(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("use_thumbnail_id")
    val useThumbnailId: Boolean
)