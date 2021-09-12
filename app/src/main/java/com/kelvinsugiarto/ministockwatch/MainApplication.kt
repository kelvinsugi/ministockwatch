package com.kelvinsugiarto.ministockwatch

import android.app.Application
import org.koin.core.context.startKoin

import com.kelvinsugiarto.ministockwatch.data.di.module.dataModule
import com.kelvinsugiarto.ministockwatch.di.module.appModule
import com.kelvinsugiarto.ministockwatch.ui.di.module.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin{
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                dataModule,
                appModule,
                uiModule
            )
        }
    }

    companion object {
        lateinit var instance: MainApplication
    }
}