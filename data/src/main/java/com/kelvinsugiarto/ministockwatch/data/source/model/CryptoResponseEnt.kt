package com.kelvinsugiarto.ministockwatch.data.source.model
import com.google.gson.annotations.SerializedName

data class CryptoResponseEnt(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfoResponseEnt,
    @SerializedName("DISPLAY")
    val display: DisplayResponseEnt?,
    @SerializedName("RAW")
    val raw: RawResponseEnt?
)
