package com.mercadolibre.cristhianbonilla.mercadolibre.di.module

import android.content.Context
import com.mercadolibre.cristhianbonilla.data.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)


        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val requestBuilder = original.newBuilder().url(originalHttpUrl)
            requestBuilder.addHeader("Authorization", "Bearer")
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Accept", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }


        return httpClient.build()
    }
}