package com.kelvinsugiarto.ministockwatch.data.source.remote

import com.kelvinsugiarto.ministockwatch.data.source.remote.interceptor.ApiKeyInterceptor
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.messageadapter.moshi.MoshiMessageAdapter
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object ScarletFactory {
    private val BACKOFF_STRATEGY = ExponentialWithJitterBackoffStrategy(5000, 5000)

    fun provideScarlet(baseUrl: String, apiKey: String): Scarlet {
        return Scarlet.Builder().webSocketFactory(httpClient(apiKey).newWebSocketFactory(baseUrl))
            .addMessageAdapterFactory(MoshiMessageAdapter.Factory())
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .backoffStrategy(BACKOFF_STRATEGY)
//            .lifecycle(createAppForegroundAndUserLoggedInLifecycle())
            .build()
    }



//    fun createAppForegroundAndUserLoggedInLifecycle(application:Application): Lifecycle {
//        return AndroidLifecycle.ofApplicationForeground(application)
//            .combineWith(loggedInLifecycle)
//    }

    private fun httpClient(apiKey: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                apiKeyInterceptor(
                    apiKey
                )
            )
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun apiKeyInterceptor(apiKey: String): Interceptor {
        return ApiKeyInterceptor(apiKey)
    }
}