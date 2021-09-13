package com.kelvinsugiarto.ministockwatch.ui.ui.login

sealed class LoginResult<out T:  Any> {
    data class Success<out T : Any>(val data: T) : LoginResult<T>()
    data class Error(val exception: String) : LoginResult<Nothing>()
}
