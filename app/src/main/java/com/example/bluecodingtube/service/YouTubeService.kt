package com.example.bluecodingtube.service


import com.example.bluecodingtube.data.Medium
import com.example.bluecodingtube.data.YoutubeVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YouTubeService {
    @GET("search")
    suspend fun getYouTubeVideos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Call<YoutubeVideo>

    @GET("search")
     fun getYoutubeVideosSearch(
        @Header("Authorization") apiKey: String?,
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<YoutubeVideo>
}

