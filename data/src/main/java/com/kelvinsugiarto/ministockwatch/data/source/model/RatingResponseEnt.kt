package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName

data class RatingResponseEnt(
    @SerializedName("Weiss")
    val weiss: WeissResponseEnt
)