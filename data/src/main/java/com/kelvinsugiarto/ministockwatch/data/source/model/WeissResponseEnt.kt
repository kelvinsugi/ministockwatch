package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName

data class WeissResponseEnt(
    @SerializedName("MarketPerformanceRating")
    val marketPerformanceRating: String,
    @SerializedName("Rating")
    val rating: String,
    @SerializedName("TechnologyAdoptionRating")
    val technologyAdoptionRating: String
)