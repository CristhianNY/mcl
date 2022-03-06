package com.mercadolibre.cristhianbonilla.data

import com.mercadolibre.cristhianbonilla.support.config.DispatcherProvider
import com.mercadolibre.cristhianbonilla.support.config.DispatcherProviderImpl
import com.mercadolibre.cristhianbonilla.support.config.Result
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseDataSource {

    var dispatcherProvider: DispatcherProvider = DispatcherProviderImpl()

    protected suspend fun <T> executeNetworkAction(action: suspend () -> T): T =
        withContext(dispatcherProvider.network) { action() }

    protected inline fun <reified T> getResult(call: () -> Response<T>): Result<T> {
        var code = 0

        try {
            val response = call()
            code = response.code()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(response.body())
            }
            return error(
                Exception(response.message()), code, response.errorBody()?.string().orEmpty(),
                response.body()
            )
        } catch (e: Exception) {
            return error(e, code, "")
        }
    }

    fun <T> error(e: Exception, code: Int, errorBody: String, data: T? = null): Result<T> {
        return Result.error(e, code, errorBody, data)
    }
}
