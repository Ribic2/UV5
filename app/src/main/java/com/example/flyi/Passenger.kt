package com.example.flyi

import com.google.gson.annotations.SerializedName

data class Passenger(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("age")
    val age: String,
    @SerializedName("houseNumber")
    val houseNumber: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
)
