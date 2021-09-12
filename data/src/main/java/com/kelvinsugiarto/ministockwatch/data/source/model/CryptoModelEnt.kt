package com.kelvinsugiarto.ministockwatch.data.source.model

/**
 * Created by bobbyirawan09 on 25/08/20.
 */
data class CryptoModelEnt(
    var name: String,
    var fullName: String,
    var currentPrice: Double,
    var changePrice: Double,
    var changePricePercentage: Double
)