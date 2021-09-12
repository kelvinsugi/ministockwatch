package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName

data class RawResponseEnt(
    @SerializedName("USD")
    val rawDetail: RawDetailResponseEnt
)