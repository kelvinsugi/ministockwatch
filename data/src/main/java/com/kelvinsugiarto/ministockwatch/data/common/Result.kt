package com.kelvinsugiarto.ministockwatch.data.common

sealed class Result {
    sealed class Success<T>:Result(){
        class Data<T>(val data: T):Success<T>()
    }
    sealed class Failure<T>:Result(){
        class ErrorMessage<T>(val errorMessage: T):Failure<T>()
    }

    sealed class State : Result() {
        object Initial : State()
        object Loading : State()
        object NoInternet : State()
    }
}