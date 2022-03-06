package com.mercadolibre.cristhianbonilla.mercadolibre.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository
import com.mercadolibre.cristhianbonilla.mercadolibre.extensions.asLiveData
import com.mercadolibre.cristhianbonilla.support.config.ResultDomain
import com.mercadolibre.cristhianbonilla.support.config.SingleMutableLiveData
import javax.inject.Inject
import kotlinx.coroutines.launch

class SearchViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    private val _state = SingleMutableLiveData<SearchState>()
    val state = _state.asLiveData()

    fun getProductsByName(name: String) {
        if (name.isEmpty()) {
            setState(SearchState.EmptyBox)
        } else {
            setState(SearchState.Loading)
            viewModelScope.launch {
                setState(getProductsByNameState(name))
            }
        }
    }

    private suspend fun getProductsByNameState(name: String) =
        when (val response = repository.getProductsByName(name)) {
            is ResultDomain.Success -> SearchState.ProductSuccess(response.data?.products.orEmpty())
            is ResultDomain.Error -> SearchState.GenericError
        }

    private fun setState(state: SearchState) {
        _state.value = state
    }
}