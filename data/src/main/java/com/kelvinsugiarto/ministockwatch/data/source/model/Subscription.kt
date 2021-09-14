package com.kelvinsugiarto.ministockwatch.data.source.model

import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("action")
    var action: String,
    @SerializedName("subs")
    var subs: List<String>
)