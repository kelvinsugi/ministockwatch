package com.kelvinsugiarto.ministockwatch.data.source.model

/**
 * Created by bobbyirawan09 on 26/08/20.
 */
data class CryptoRequestEnt(
    var pageNum: Int,
    var limit: Int = 10,
    var tsym: String = "USD"
)