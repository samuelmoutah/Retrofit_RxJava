package com.rjt.retrofit.model

import com.google.gson.annotations.SerializedName

data class CategoryModel (
    @SerializedName("price") val price: Double,
    @SerializedName("productName") val productName: String,
    @SerializedName("description") val description: String
)
