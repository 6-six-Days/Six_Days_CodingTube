package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.BuildConfig
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CCategoryViewModel: ViewModel() {
    private val Cvideo= MutableLiveData<PlayList?>()
    val cvideo=Cvideo
    private val CisLoading = MutableLiveData<Boolean>()
    val cisLoading = CisLoading
    private val Cmessage = MutableLiveData<String>()
    val cmessage =Cmessage

    val keyword = "C programming "
    val maxResults = 20
    val part = "snippet"
    val apiKey = BuildConfig.YOUTUBE_API_KEY
    val order="viewCount"


    init{
        getC()
    }
    fun getC() {
        cisLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults,apiKey, order)
        client.enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    cvideo.value = data

                } else {

                    cmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                cmessage.value = "Error: ${t.message}"
                cisLoading.value = false
            }
        })
    }


}