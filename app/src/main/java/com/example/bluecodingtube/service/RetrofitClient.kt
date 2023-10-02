package com.example.bluecodingtube.service

import android.app.Service
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    val searchService: searchRequest by lazy {
        retrofit.create(searchRequest::class.java)
    }

}
