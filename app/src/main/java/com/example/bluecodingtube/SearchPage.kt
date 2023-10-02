package com.example.bluecodingtube

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.databinding.FragmentSearchPageBinding
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.service.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    }

    private fun fetchYoutubeVideos(query: String) {

        adapter.clearItem()

        val service = RetrofitClient.searchService
        val apiKey = "AIzaSyBfaJPCzYR-ff1z4Xbx0lVGwoS6hpS2Sj8"
        service.getYoutubeVideosSearch(apiKey, query, 1, 80)
            .enqueue(object : retrofit2.Callback<Snippet> {
                override fun onResponse(
                    call: Call<Snippet>,
                    response: Response<Snippet>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        body?.items?.let { items ->
                            val thumbnails = items.mapNotNull {
                                it.snippet?.thumbnails?.medium?.url}
                            val titles = items.mapNotNull {
                                it.snippet?.title
                            }
                            searchItem.add(searchData(thumbnails,titles))
                        }

                        adapter.items = searchItem
                        adapter.notifyDataSetChanged()

                    } else {
                        Log.e("SearchPage", "API 요청이 실패했습니다.")
                    }
                }

                override fun onFailure(call: Call<Snippet>, t: Throwable) {
                    Log.e("SearchPage", "API 요청 중 오류 발생", t)
                }
            })
    }


}


