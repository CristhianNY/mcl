package com.mercadolibre.cristhianbonilla.mercadolibre

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.domain.model.SearchResultModel
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository
import com.mercadolibre.cristhianbonilla.mercadolibre.search.SearchState
import com.mercadolibre.cristhianbonilla.mercadolibre.search.SearchViewModel
import com.mercadolibre.cristhianbonilla.support.config.GenericError
import com.mercadolibre.cristhianbonilla.support.config.ResultDomain
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var stateObserver: Observer<SearchState?>


    @Mock
    private lateinit var productRepository: ProductRepository

    private lateinit var searchViewModel: SearchViewModel

    private val productName = "Celular"


    @Before
    fun setUp() {
        searchViewModel = SearchViewModel(productRepository)
    }


    @Test
    fun `Get products by name success`(): Unit = testCoroutineRule.runBlockingTest {
        // given
        val mockSearchResult = SearchResultModel(
            listOf(
                ProductModel(
                    true,
                    100.0,
                    "US",
                    "123",
                    10.00,
                    "image.png",
                    "imagen.png",
                    "POCO X3 Pro",
                    true
                )
            )
        )
        val resultMocked = ResultDomain.Success(mockSearchResult)


        // when
        whenever(productRepository.getProductsByName(productName)).thenReturn(resultMocked)
        searchViewModel.state.observeForever(stateObserver)

        searchViewModel.getProductsByName(productName)

        // then
        argumentCaptor<SearchState> {
            verify(stateObserver, times(2)).onChanged(capture())
            Assert.assertEquals(SearchState.Loading, allValues.first())
            Assert.assertEquals(
                SearchState.ProductSuccess(mockSearchResult.products),
                allValues.last()
            )
        }

    }

    @Test
    fun `Get products by name error`(): Unit = testCoroutineRule.runBlockingTest {

        val exception = ResultDomain.Error(GenericError)
        whenever(productRepository.getProductsByName(productName)).thenReturn(exception)

        searchViewModel.state.observeForever(stateObserver)

        searchViewModel.getProductsByName(productName)

        argumentCaptor<SearchState> {
            verify(stateObserver, times(2)).onChanged(capture())
            Assert.assertEquals(SearchState.Loading, allValues.first())
            Assert.assertEquals(
                SearchState.GenericError, allValues.last()
            )
        }
    }
}