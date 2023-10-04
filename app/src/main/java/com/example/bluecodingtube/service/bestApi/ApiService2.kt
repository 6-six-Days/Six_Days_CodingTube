package com.example.bluecodingtube.service.bestApi

import com.example.bluecodingtube.data.PlayList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService2 {
    @GET("search")
    fun searchVideos(
        @Query("q") keyword: String,
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String="AIzaSyA_lmB1mbVwP59a7hVV1NtQGZtRnATlIuo",
        @Query("order") order: String

    ): Call<PlayList>
}