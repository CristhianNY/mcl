package com.mercadolibre.cristhianbonilla.mercadolibre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.mercadolibre.extensions.asLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    private val _productList = MutableLiveData<List<ProductModel>>()
    val productList = _productList.asLiveData()


    fun setProductList(productList: List<ProductModel>) {
        _productList.value = productList
    }
}