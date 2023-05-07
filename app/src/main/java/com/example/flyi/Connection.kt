package com.example.flyi

import com.google.gson.annotations.SerializedName

data class Connection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("to")
    val to: String,
    @SerializedName("from")
    val from: String,
    @SerializedName("kids")
    val kids: Boolean,
    @SerializedName("passengers")
    val passengers: Int,
    @SerializedName("length")
    val length: Double,
    @SerializedName("isOneWay")
    val isOneWay: Boolean,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("arrival")
    val arrival: String,
    @SerializedName("classes")
    val classes: Array<Classes>,
)