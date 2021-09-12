package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.source.remote.interceptor.ApiKeyInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitFactory {
    private fun provideOKHttp(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor(apiKeyParamsInterceptor(apiKey))
            .connectTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofit(baseUrl: String, apiKey: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOKHttp(apiKey))
            .build()
    }

    private fun httpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun apiKeyParamsInterceptor(apiKey: String): Interceptor {
        return ApiKeyInterceptor(apiKey)
    }

}