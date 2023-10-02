package com.example.bluecodingtube.service

import com.example.bluecodingtube.data.Snippet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface searchRequest{
    @GET("search")
    fun getYoutubeVideosSearch(
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<Snippet>

}