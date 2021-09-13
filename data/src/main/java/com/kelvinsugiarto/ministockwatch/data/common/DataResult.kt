package com.kelvinsugiarto.ministockwatch.data.common

sealed class DataResult<out T:  Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val exception: String) : DataResult<Nothing>()
//
//    sealed class State : DataResult<Any>() {
//        object Initial : State()
//        object Loading : State()
//        object NoInternet : State()
//    }

    fun handleResult(
        successDataBlock: (T) -> Unit = {},
        failureBlock: (Error) -> Unit = {},
    ) {
        when (this) {
            is Success -> successDataBlock(this.data)
            is Error -> failureBlock(Error(exception))
        }
    }
}