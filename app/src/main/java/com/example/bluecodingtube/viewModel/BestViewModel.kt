package com.example.bluecodingtube.viewModel

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.BuildConfig
import com.example.bluecodingtube.data.Items
import com.example.bluecodingtube.data.PageInfo
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.data.YoutubeVideo
import com.example.bluecodingtube.service.RetrofitClient
import com.example.bluecodingtube.service.bestApi.ApiConfig
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestViewModel():ViewModel() {



    private val _video= MutableLiveData<PlayList?>()
    val video=_video
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _message = MutableLiveData<String>()
    val message = _message

    val keyword = "programming"
    val maxResults = 20
    val part = "snippet"
    val apiKey =BuildConfig.YOUTUBE_API_KEY
    val order= "relevance"

    init{
        getBest()
    }
    fun getBest() {
        isLoading.value = true
        val client=ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey, order)
        client.enqueue(object : Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    video.value = data

                } else {

                    message.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                _message.value = "Error: ${t.message}"
                _isLoading.value = false
            }
        })
    }


}