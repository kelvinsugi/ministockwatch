package com.kelvinsugiarto.ministockwatch.di.module

import com.kelvinsugiarto.ministockwatch.BuildConfig.API_KEY
import com.kelvinsugiarto.ministockwatch.BuildConfig.BASE_URL
import com.kelvinsugiarto.ministockwatch.data.source.remote.RetrofitFactory
import org.koin.dsl.module

val appModule = module {
    single {
        RetrofitFactory.provideRetrofit(BASE_URL, API_KEY)
    }
}