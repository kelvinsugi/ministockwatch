package com.kelvinsugiarto.ministockwatch.data.source.model

data class CryptoLiveFeedDataEnt(
    var type: Int? = 0,
    var symbol: String? = "",
    var topTierFullVolume: Double? = 0.0
)