package com.example.bluecodingtube.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.service.bestApi.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KCategoryViewModel:ViewModel() {


    private val Kvideo= MutableLiveData<PlayList?>()
    val kvideo=Kvideo
    private val KisLoading = MutableLiveData<Boolean>()
    val kisLoading = KisLoading
    private val Kmessage = MutableLiveData<String>()
    val kmessage =Kmessage

    val keyword = "Kotlin laungauge"
    val maxResults = 20
    val part = "snippet"
    val apiKey = "AIzaSyABG7Q5R8daPBnggYQf1gKO6F965Opr80Y"
    val order="viewCount"


    init{
        getJava()
    }
    fun getJava() {
        kisLoading.value = true
        val client= ApiConfig.getService().searchVideos(keyword, part, maxResults, apiKey, order)
        client.enqueue(object : Callback<PlayList> {
            override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    kvideo.value = data

                } else {

                    kmessage.value = "Failed"
                }

            }

            override fun onFailure(call: Call<PlayList>, t: Throwable) {
                // 실패 시 메시지 설정
                kmessage.value = "Error: ${t.message}"
                kisLoading.value = false
            }
        })
    }


}