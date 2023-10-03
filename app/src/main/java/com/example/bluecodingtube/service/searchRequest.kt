package com.example.bluecodingtube.service

import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.data.YoutubeVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface searchRequest{
    @GET("search")
    fun getYoutubeVideosSearch(
        @Query("key") apiKey: String = "AIzaSyBfaJPCzYR-ff1z4Xbx0lVGwoS6hpS2Sj8",
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Call<YoutubeVideo>

}