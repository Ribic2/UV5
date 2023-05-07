package com.example.flyi

import com.google.gson.annotations.SerializedName

data class Classes(
    @SerializedName("name")
    val name: String,
    @SerializedName("priceAdult")
    val priceAdult: Int,
    @SerializedName("priceKid")
    val priceKid: Int,
    @SerializedName("priceTeen")
    val priceTeen: Int,
)
