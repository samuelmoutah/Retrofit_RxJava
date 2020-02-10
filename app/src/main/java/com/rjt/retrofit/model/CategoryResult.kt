package com.rjt.retrofit.model

import com.google.gson.annotations.SerializedName

data class CategoryResult (
    @SerializedName("data")
    val data: ArrayList<CategoryModel>,

    @SerializedName("error")
    val error: String
)