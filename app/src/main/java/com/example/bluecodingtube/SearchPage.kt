package com.example.bluecodingtube

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bluecodingtube.adapter.SearchPageAdapter
import com.example.bluecodingtube.data.YoutubeVideo
import com.example.bluecodingtube.databinding.FragmentSearchPageBinding
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.service.RetrofitClient
import com.example.bluecodingtube.service.api.SixDays
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class SearchPage : Fragment() {

    private lateinit var binding: FragmentSearchPageBinding
    private lateinit var searchContext: Context
    private lateinit var adapter: SearchPageAdapter

    private var searchItem: ArrayList<searchData> = ArrayList()



    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        setupView()
        setupListener()
        return binding.root
    }

    private fun setupView() {
        val spanCount = 2
        val grid = GridLayoutManager(searchContext, spanCount)
        binding.searchRecycleView.layoutManager = grid
        binding.searchRecycleView.itemAnimator = null

        adapter = SearchPageAdapter(searchContext)
        binding.searchRecycleView.adapter = adapter
    }




    private fun setupListener() {
        val searchText = binding.searchtext
        val searchButton = binding.searchbutton

        searchButton.setOnClickListener {
            val query = searchText.text.toString()
            if (query.isNotEmpty()) {
                Log.d("확인", "check")

                CoroutineScope(Dispatchers.Main).launch {
                    fetchYoutubeVideos(query)
                }
            }
        }

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.searchtext.windowToken, 0)

        val keywordButtons = listOf(binding.keyword1, binding.keyword2, binding.keyword3)

        keywordButtons.forEach{keywordButtons ->
            keywordButtons.setOnClickListener{
                val keyword = keywordButtons.text.toString()
                filterSearchResults(keyword)
                Log.d("키워드","${keyword}")
            } // Keyword 확인
        }
    }


    private fun filterSearchResults(keyword: String) {


            adapter.clear()
            fetchYoutubeVideos(query = keyword)

    }


    private fun fetchYoutubeVideos(query: String) {

        adapter.clear()

        val service = RetrofitClient.searchService
//query 비워짐, this.query 공백 데이터 로 받아옴,필수 요소 snippet 비워져 있음,(필수 요소 중요) 404 error 실패한 이유 확인 breakpoint ${response} -> 값이 잘려 있을 때 error log 로 변환 .~ (breakpoint 안찍어도 됨)
        service.getYoutubeVideosSearch(apiKey = SixDays.getApp().getString(R.string.YouTube_API_Key),query = query, videoOrder = "date", maxResults = 50, channelId = "")
            .enqueue(object : retrofit2.Callback<YoutubeVideo> {
                override fun onResponse(
                    call: Call<YoutubeVideo?>,
                    response: Response<YoutubeVideo?>,
                ){
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.items?.forEach() { item ->
                            val thumbnails = item.snippet.thumbnails.medium.url
                            val titles = item.snippet.title
                            val info = item.snippet.description
                            val date = item.snippet.publishTime

                            searchItem.add(searchData(thumbnails, titles,info,date))
                            Log.d("썸네일", "${thumbnails}")
                            Log.d("타이틀", "${titles}")
                            Log.d("설명", "${info}")
                            Log.d("날짜", "${date}")
                        }

                    } else {
                        Log.e("SearchPage", "API 요청 오류 ${response.errorBody()}" ) // error log 는 e
                    }

                    adapter.items = searchItem
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<YoutubeVideo>, t: Throwable) {
                    Log.e("SearchPage", "API 요청 중 오류 발생", t)
                }
            })
    }


}




