package com.kelvinsugiarto.ministockwatch.data.source.model


data class CryptoRequestEnt(
    var pageNum: Int,
    var limit: Int = 10,
    var tsym: String = "USD"
)