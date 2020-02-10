package com.rjt.retrofit.network

import com.rjt.retrofit.model.CategoryResult
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products/1")

    fun getCategoriesAsync() : Call<CategoryResult> // create call object to get network call

    @GET("products/1")
    fun getProducts() : Single<CategoryResult> //single observable
}