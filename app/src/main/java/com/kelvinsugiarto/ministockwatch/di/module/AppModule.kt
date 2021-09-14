package com.kelvinsugiarto.ministockwatch.di.module

import com.kelvinsugiarto.ministockwatch.BuildConfig.*
import com.kelvinsugiarto.ministockwatch.data.source.remote.RetrofitFactory
import com.kelvinsugiarto.ministockwatch.data.source.remote.ScarletFactory
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitFactory.provideRetrofit(BASE_URL, API_KEY)
    }

//    single {
//        ScarletFactory.provideScarlet(BASE_URL_WEBSOCKET,API_KEY)
//    }
}