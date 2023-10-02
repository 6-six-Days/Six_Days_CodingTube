package com.example.bluecodingtube.service.bestApi


import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.data.YoutubeVideo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("channels")
    fun getChannel(
        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<PlayList>

}