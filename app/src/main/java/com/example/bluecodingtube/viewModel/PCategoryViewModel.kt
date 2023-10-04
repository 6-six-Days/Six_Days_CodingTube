package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.BuildConfig
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PCategoryViewModel:ViewModel() {

    private val Pvideo= MutableLiveData<PlayList?>()
    val pvideo=Pvideo
    private val PisLoading = MutableLiveData<Boolean>()
    val pisLoading = PisLoading
    private val Pmessage = MutableLiveData<String>()
    val pmessage =Pmessage

    val keyword = "PYTHON laungauge"
    val maxResults = 20
    val part = "snippet"
    val order="relevance"
    val apiKey = BuildConfig.YOUTUBE_API_KEY


    init{
        getPython()
    }
    fun getPython() {
        pisLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey , order)
        client.enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    pvideo.value = data

                } else {

                    pmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                pmessage.value = "Error: ${t.message}"
                pisLoading.value = false
            }
        })
    }


}
