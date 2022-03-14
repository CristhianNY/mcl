package com.mercadolibre.cristhianbonilla.data.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercadolibre.cristhianbonilla.data.TestCoroutineRule
import com.mercadolibre.cristhianbonilla.data.entity.ProductEntity
import com.mercadolibre.cristhianbonilla.data.entity.SearchResultEntity
import com.mercadolibre.cristhianbonilla.data.entity.toModel
import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSource
import com.mercadolibre.cristhianbonilla.data.products.repository.ProductRepositoryImpl
import com.mercadolibre.cristhianbonilla.domain.repository.ProductRepository
import com.mercadolibre.cristhianbonilla.support.config.GenericError
import com.mercadolibre.cristhianbonilla.support.config.Result
import com.mercadolibre.cristhianbonilla.support.config.ResultDomain
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import java.net.HttpURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryTest {

    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var dataSource: ProductDataSource

    private lateinit var repository: ProductRepository

    private val productName = "Celular"

    @Before
    fun setUp() {
        repository = ProductRepositoryImpl(dataSource)
    }

    @Test
    fun `Get products by name success`() = testCoroutineRule.runBlockingTest {
        val resultMocked = SearchResultEntity(
            listOf(
                ProductEntity(
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
        val expectedResult = ResultDomain.Success(resultMocked.toModel())
        // given
        whenever(dataSource.getProductsByName(productName)).thenReturn(Result.success(resultMocked))
        // when
        val result = repository.getProductsByName(productName)

        // then
        verify(dataSource).getProductsByName(productName)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `Get products by name error`() = testCoroutineRule.runBlockingTest {
        val tokenError = Result.error<SearchResultEntity>(
            Exception(),
            HttpURLConnection.HTTP_INTERNAL_ERROR
        )
        // given
        whenever(dataSource.getProductsByName(productName)).thenReturn(tokenError)
        // when
        val result = repository.getProductsByName(productName)

        // then
        verify(dataSource).getProductsByName(productName)
        Assert.assertTrue(result is ResultDomain.Error)
        Assert.assertTrue((result as ResultDomain.Error)?.error == GenericError)
    }
}