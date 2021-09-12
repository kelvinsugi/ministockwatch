package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName

data class DisplayResponseEnt(
    @SerializedName("IDR")
    val displayDetail: DisplayDetailResponseEnt
)