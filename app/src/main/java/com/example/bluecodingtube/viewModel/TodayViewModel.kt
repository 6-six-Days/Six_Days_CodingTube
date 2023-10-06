package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodayViewModel:ViewModel(){

    private val Tvideo= MutableLiveData<PlayList?>()
    val tvideo=Tvideo
    private val TisLoading = MutableLiveData<Boolean>()
    val tisLoading = TisLoading
    private val Tmessage = MutableLiveData<String>()
    val tmessage =Tmessage

    val keyword = "Programming lecture"
    val maxResults = 20
    val part = "snippet"
    val order="viewCount"
    val apiKey = "AIzaSyDWsci-1uJ0aBaJXP-Y_rFowIlcUNhMHxI"

    init{
        getJava()
    }
    fun getJava() {
        tisLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey, order)
        client.enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    tvideo.value = data

                } else {

                    tmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                tmessage.value = "Error: ${t.message}"
                tisLoading.value = false
            }
        })
    }


}
