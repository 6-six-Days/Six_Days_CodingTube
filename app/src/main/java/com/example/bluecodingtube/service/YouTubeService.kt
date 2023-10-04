package com.example.bluecodingtube.service


import com.example.bluecodingtube.data.Medium
import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.data.YoutubeVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.time.Duration

interface YouTubeService {
    @GET("search")
     fun getYouTubeVideos(
        @Query("key") apiKey: String="AIzaSyABG7Q5R8daPBnggYQf1gKO6F965Opr80Y",
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Call<YoutubeVideo>

}

