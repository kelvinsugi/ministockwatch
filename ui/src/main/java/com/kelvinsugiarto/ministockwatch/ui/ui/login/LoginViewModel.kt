package com.kelvinsugiarto.ministockwatch.ui.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvinsugiarto.ministockwatch.ui.utils.SessionManager
import kotlinx.coroutines.*
import kotlin.math.log



class LoginViewModel(private val sessionManager: SessionManager) : ViewModel() {

    private var _loginObservable = MutableLiveData<LoginResult<*>>()
    val loginObservable = _loginObservable as LiveData<LoginResult<*>>

    fun doLogin(email: String)  {
        sessionManager.startUserSession()
        sessionManager.email = email.orEmpty()
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000L)
            val loginResult = LoginResult.Success("Success")
            _loginObservable.postValue(loginResult)
        }

    }
}