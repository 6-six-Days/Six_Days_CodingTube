package com.example.bluecodingtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluecodingtube.adapter.BestRecyclerViewAdapter
import com.example.bluecodingtube.adapter.CategoryRecyclerViewAdapter
import com.example.bluecodingtube.adapter.WordCategoryRecyclerViewAdapter
import com.example.bluecodingtube.databinding.FragmentHomePageBinding
import com.example.bluecodingtube.databinding.FragmentSearchPageBinding
import com.example.bluecodingtube.dataclass.dummydata


class HomePage : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var bestAdapter: BestRecyclerViewAdapter
    private lateinit var wordCategoryAdapter: WordCategoryRecyclerViewAdapter
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val wordCategoryView = binding.worldcategoryRecyclerView
        val bestRecyclerView = binding.BestRecyclerView
        val categoryView = binding.CategoryRecyclerView
        val itemList = dummydata.dummyList()

        // BestRecyclerView 초기화
        bestAdapter = BestRecyclerViewAdapter(itemList)
        bestRecyclerView.adapter = bestAdapter
        bestRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // WordCategoryRecyclerView 초기화
        wordCategoryAdapter = WordCategoryRecyclerViewAdapter(itemList)
        wordCategoryView.adapter = wordCategoryAdapter
        wordCategoryView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // CategoryRecyclerView 초기화
        categoryAdapter = CategoryRecyclerViewAdapter(itemList)
        categoryView.adapter = categoryAdapter
        categoryView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 뷰 바인딩 참조 해제
    }
}