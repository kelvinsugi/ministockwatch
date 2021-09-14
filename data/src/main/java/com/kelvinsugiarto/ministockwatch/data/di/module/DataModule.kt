package com.kelvinsugiarto.ministockwatch.data.di.module

import com.kelvinsugiarto.ministockwatch.data.source.CryptoRepositoryImpl
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoAPI
import com.kelvinsugiarto.ministockwatch.data.source.remote.CryptoRepository
import com.kelvinsugiarto.ministockwatch.data.source.remote.WebsocketAPI
import com.tinder.scarlet.Scarlet
import org.koin.dsl.module
import retrofit2.Retrofit

    val dataModule = module {
        single { get<Retrofit>().create(CryptoAPI::class.java)  }

        single<CryptoRepository> {
            CryptoRepositoryImpl(get())
        }

//        single {
//            get<Scarlet>().create(WebsocketAPI::class.java)
//        }
    }
