package com.example.bluecodingtube

import android.app.appsearch.AppSearchManager.SearchContext
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
        setupListener()

        return binding.root
    }

    private fun setupView(){

        val spanCount = 2
        val grid = GridLayoutManager(SearchContext,spanCount)

        binding.searchRecycleView.layoutManager = grid

        binding.searchRecycleView.adapter = adapter
        binding.searchRecycleView.itemAnimator = null

    }

    private fun setupListener(){
        val searchText = binding.searchtext
        val searchButton = binding.searchbutton

        searchButton.setOnClickListener{
            val query = searchText.text.toString()
            if(query.isNotEmpty()){

            }
        }
    }

}