package com.example.bluecodingtube

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bluecodingtube.adapter.SearchPageAdapter
import com.example.bluecodingtube.adapter.searchData
import com.example.bluecodingtube.databinding.FragmentSearchPageBinding


class SearchPage : Fragment() {

    private lateinit var binding: FragmentSearchPageBinding
    private lateinit var SearchContext : Context
    private lateinit var adapter: SearchPageAdapter

    private var searchItem : ArrayList<searchData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        ): View? {
         binding = FragmentSearchPageBinding.inflate(inflater, container, false)

        setupView()
       // setupListener()

        return binding.root
    }

    private fun setupView(){

        val spanCount = 2
        val grid = GridLayoutManager(SearchContext,spanCount)

        binding.searchRecycleView.layoutManager = grid

        binding.searchRecycleView.adapter = adapter
        binding.searchRecycleView.itemAnimator = null

    }

    // 데이터 값 실험
    private fun generateDummyData(): List<searchData> {
        val dummyData = mutableListOf<searchData>()
        for (i in 1..20) {
            val imageResourceId = when (i) {
                1 -> R.drawable.search_pic_item
                2 -> R.drawable.search_pic_item
                // 나머지 이미지에 대한 리소스 ID를 설정
                else -> R.drawable.search_pic_item // 기본 이미지 리소스 ID를 설정
            }
            val searchData = searchData(imageResourceId, "Love lee")
            dummyData.add(searchData)
        }
        return dummyData
    }


    /* 검색
    private fun setupListener(){
        val searchText = binding.searchtext
        val searchButton = binding.searchbutton

        searchButton.setOnClickListener{
            val query = searchText.text.toString()
            if(query.isNotEmpty()){

            }
        }
    }
*/
}