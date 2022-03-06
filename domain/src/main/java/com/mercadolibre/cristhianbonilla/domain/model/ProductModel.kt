package com.mercadolibre.cristhianbonilla.domain.model

data class ProductModel(
    val acceptsMercadopago: Boolean,
    val availableQuantity: Double,
    val currencyId: String,
    val id: String,
    val price: Double,
    val thumbnail: String,
    val thumbnailId: String,
    val title: String,
    val useThumbnailId: Boolean
)