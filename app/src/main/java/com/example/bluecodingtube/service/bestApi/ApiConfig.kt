package com.example.bluecodingtube.service.bestApi

import android.util.Log
import com.itkacher.okprofiler.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getService(): ApiService2 {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                )

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val url = chain
                        .request()
                        .url
                        .newBuilder()
                        .addQueryParameter("part", "snippet")
                        .addQueryParameter("maxResults", "20")
                        .addQueryParameter("q", "cording")
                        .addQueryParameter("key", "AIzaSyDWsci-1uJ0aBaJXP-Y_rFowIlcUNhMHxI")
                        .addQueryParameter("order", "viewCount")
                        .addQueryParameter("type", "video")
                        .build()
                    chain.proceed(chain.request().newBuilder().url(url).build())
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService2::class.java)

        }
    }

}