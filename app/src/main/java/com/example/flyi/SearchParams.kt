package com.example.flyi

import com.google.gson.annotations.SerializedName

data class SearchParams(
    @SerializedName("from")
    val from: String?,
    @SerializedName("to")
    val to: String?,
    @SerializedName("departure")
    val departure: String?,
    @SerializedName("arrival")
    val arrival: String?,
    @SerializedName("passengers")
    val passengers: Int?,
    @SerializedName("one_way")
    val one_way: String?,
    @SerializedName("round_trip")
    val round_trip: String?
)
