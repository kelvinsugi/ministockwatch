package com.kelvinsugiarto.ministockwatch.ui.di.module

import android.content.SharedPreferences
import android.se.omapi.Session
import com.kelvinsugiarto.ministockwatch.ui.ui.login.LoginViewModel
import com.kelvinsugiarto.ministockwatch.ui.ui.watchlist.WatchListViewModel
import com.kelvinsugiarto.ministockwatch.ui.utils.Constants.SHARED_PREFERENCE_NAME
import com.kelvinsugiarto.ministockwatch.ui.utils.SessionManager
import com.kelvinsugiarto.ministockwatch.ui.utils.SharedPreferenceHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val uiModule = module {
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(SHARED_PREFERENCE_NAME, 0)
    }

    single {
        SharedPreferenceHelper(get())
    }

    single {
        SessionManager(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel{
        WatchListViewModel(get())
    }
}
