package com.example.bluecodingtube.service

import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
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
            //제이슨은 코틀린으로 변환

            .build()
    }
    val search : YouTubeService by lazy {
        retrofit.create(YouTubeService::class.java)
    }

    


}
