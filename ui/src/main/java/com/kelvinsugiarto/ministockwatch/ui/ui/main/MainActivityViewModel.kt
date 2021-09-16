package com.kelvinsugiarto.ministockwatch.ui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kelvinsugiarto.ministockwatch.ui.ui.login.LoginResult
import com.kelvinsugiarto.ministockwatch.ui.utils.SessionManager

class MainActivityViewModel(private val sessionManager: SessionManager) : ViewModel() {
    private var _loginStateObservable = MutableLiveData<Boolean>()
    val loginStateObservable = _loginStateObservable as LiveData<Boolean>

    fun doLogout(){
        sessionManager.endUserSession()
        _loginStateObservable.postValue(false)
    }

}