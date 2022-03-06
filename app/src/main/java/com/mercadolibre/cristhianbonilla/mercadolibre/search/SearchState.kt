package com.mercadolibre.cristhianbonilla.mercadolibre.search

import com.mercadolibre.cristhianbonilla.domain.model.ProductModel

sealed class SearchState {
    object Loading : SearchState()
    data class ProductSuccess(val product: List<ProductModel>) : SearchState()
    object EmptyBox : SearchState()
    object GenericError : SearchState()
}