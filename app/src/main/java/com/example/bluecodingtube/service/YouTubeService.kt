package com.example.bluecodingtube.service


import com.example.bluecodingtube.data.YoutubeVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration

interface YouTubeService {
    @GET("search")
     fun getYouTubeVideos(
        @Query("key") apiKey: String,
        @Query("q") query: String,
        @Query("order") videoOrder: String,
        @Query("type") videoType: String = "video",
        @Query("maxResults") maxResults: Int,
        @Query("channelId") channelId: String = "",
        @Query("part") part: String = "snippet",
    ): Call<YoutubeVideo>

}

interface BestVideo{
    @GET("Video")
    fun getVideo(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order:String


    ):Call<YoutubeVideo>
}