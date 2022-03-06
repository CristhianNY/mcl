package com.mercadolibre.cristhianbonilla.domain.model

data class ProductModel(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Int,
    val currencyId: String,
    val id: String,
    val price: Int,
    val thumbnail: String,
    val thumbnailId: String,
    val title: String,
    val useThumbnailId: Boolean
)