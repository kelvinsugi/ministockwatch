package com.kelvinsugiarto.ministockwatch.data.source.model

data class CryptoModelEnt(
    var name: String,
    var fullName: String,
    var currentPrice: Double,
    var changePrice: Double,
    var changePricePercentage: Double
)