package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.BuildConfig
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicViewModel: ViewModel() {

    private val Mvideo= MutableLiveData<PlayList?>()
    val mvideo=Mvideo
    private val MisLoading = MutableLiveData<Boolean>()
    val misLoading = MisLoading
    private val Mmessage = MutableLiveData<String>()
    val mmessage =Mmessage

    val keyword = "Coding Music"
    val maxResults = 20
    val part = "snippet"
    val order="relevance"
    val apiKey = BuildConfig.YOUTUBE_API_KEY


    init{
        getJava()
    }
    fun getJava() {
        misLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey , order)
        client.enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    mvideo.value = data

                } else {

                    mmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                mmessage.value = "Error: ${t.message}"
                misLoading.value = false
            }
        })
    }


}