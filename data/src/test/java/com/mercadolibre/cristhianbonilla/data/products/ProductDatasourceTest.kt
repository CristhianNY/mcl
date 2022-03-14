package com.mercadolibre.cristhianbonilla.data.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercadolibre.cristhianbonilla.data.TestCoroutineRule
import com.mercadolibre.cristhianbonilla.data.TestDispatcherProvider
import com.mercadolibre.cristhianbonilla.data.entity.ProductEntity
import com.mercadolibre.cristhianbonilla.data.entity.SearchResultEntity
import com.mercadolibre.cristhianbonilla.data.products.api.ProductApi
import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSource
import com.mercadolibre.cristhianbonilla.data.products.data_source.ProductDataSourceImpl
import com.mercadolibre.cristhianbonilla.support.config.Result
import com.nhaarman.mockitokotlin2.whenever
import java.net.HttpURLConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductDatasourceTest {
    @Rule
    @JvmField
    val testRule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val testCoroutineRule: TestCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: ProductApi

    private lateinit var dataSource: ProductDataSource

    @Before
    fun setUp() {
        dataSource = ProductDataSourceImpl(api).apply {
            dispatcherProvider = TestDispatcherProvider()
        }
    }

    @Test
    fun getProducts_by_name_success() = testCoroutineRule.runBlockingTest {
        val productName = "Celular"
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
        val expectedResult = Result.success(resultMocked)

        //given
        whenever(api.getProductsByName(productName)).thenReturn(Response.success(resultMocked))

        // when
        val result = dataSource.getProductsByName(productName)

        // then

        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun get_products_by_name_error() = testCoroutineRule.runBlockingTest {
        val productName = "celular"
        val products = ResponseBody.create("application/json".toMediaType(), "")

        // given
        whenever(api.getProductsByName(productName)).thenReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                products
            )
        )

        // when
        val result = dataSource.getProductsByName(productName)

        Assert.assertTrue(result.status is Result.Status.ERROR)
    }

}