package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ResponseEnt(
    @SerializedName("Message")
    var message: String? = "",
    @SerializedName("Type")
    var type: Int? = 0,
    @SerializedName("MetaData")
    var metadata:Objects? = null,
    @SerializedName("SponsoredData")
    var sponsoredData:Objects? = null,
    @SerializedName("Data")
    var data: List<CryptoResponseEnt>? = null,
    @SerializedName("RateLimit")
    var rateLimit:  Objects? = null,
    @SerializedName("HasWarning")
    var hasWarning: Boolean? = false
)
