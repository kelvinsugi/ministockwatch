package com.kelvinsugiarto.ministockwatch.data.common

sealed class StateResult{
    object isLoading : StateResult()
    object isLoaded  :StateResult()
}
