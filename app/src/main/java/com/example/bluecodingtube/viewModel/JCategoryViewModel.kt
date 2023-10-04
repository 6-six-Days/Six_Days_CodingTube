package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.BuildConfig
import com.example.bluecodingtube.data.PlayList

import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JCategoryViewModel: ViewModel() {



    private val Jvideo= MutableLiveData<PlayList?>()
    val jvideo=Jvideo
    private val JisLoading = MutableLiveData<Boolean>()
    val jisLoading = JisLoading
    private val Jmessage = MutableLiveData<String>()
    val jmessage =Jmessage

    val keyword = "java laungauge"
    val maxResults = 20
    val part = "snippet"
    val apiKey = BuildConfig.YOUTUBE_API_KEY
    val order="relevance"


    init{
        getJava()
    }
    fun getJava() {
        jisLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey, order)
        client.enqueue(object : Callback<PlayList>{
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    jvideo.value = data

                } else {

                    jmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                jmessage.value = "Error: ${t.message}"
                JisLoading.value = false
            }
        })
    }


}