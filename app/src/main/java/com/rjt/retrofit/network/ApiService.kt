package com.rjt.retrofit.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


//1
class ApiService {

    companion object {

        val BASE_URL = "https://apolis-grocery.herokuapp.com/api/"


        //create retrofit instance
        fun getClient() : Retrofit{ //
            val retrofit = Retrofit.Builder() //create retrofit object using Retrofit.builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .build()

            return retrofit
        }
    }
}


// it does not work in some reason
//object ApiService {
//    private const val BASE_URL = "https://apolis-grocery.herokuapp.com/api/"
//
//    val instance: ApiInterface by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//
//        retrofit.build().create(ApiInterface::class.java)
//    }
//}