package com.example.bluecodingtube

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluecodingtube.adapter.BestRecyclerViewAdapter
import com.example.bluecodingtube.adapter.CategoryRecyclerViewAdapter
import com.example.bluecodingtube.adapter.WordCategoryRecyclerViewAdapter
import com.example.bluecodingtube.data.YoutubeVideo
import com.example.bluecodingtube.databinding.FragmentHomePageBinding
import com.example.bluecodingtube.dataclass.Item
import com.example.bluecodingtube.dataclass.dummydata
import com.example.bluecodingtube.service.RetrofitClient
import com.example.bluecodingtube.viewModel.BestViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomePage : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var bestAdapter: BestRecyclerViewAdapter
    private lateinit var wordCategoryAdapter: WordCategoryRecyclerViewAdapter
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter
    private lateinit var bestViewModel: BestViewModel





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        bestAdapter=BestRecyclerViewAdapter()
        binding.BestRecyclerView.adapter=bestAdapter
        binding.BestRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bestViewModel = ViewModelProvider(this).get(BestViewModel::class.java)
        bestViewModel?.video?.observe(viewLifecycleOwner,{
            if (it != null) {
                val playlist = listOf(it) // PlayList 객체를 리스트에 담음
                bestAdapter.setData(playlist)

            }
        })




        val wordCategoryView = binding.worldcategoryRecyclerView
        val categoryView = binding.CategoryRecyclerView
        val itemList = dummydata.dummyList()

        val dataType = listOf( "C", "JAVA", "KOTLIN", "PYTHON")
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, dataType)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedValue=dataType[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }




        wordCategoryAdapter = WordCategoryRecyclerViewAdapter(itemList)
        wordCategoryView.adapter = wordCategoryAdapter
        wordCategoryView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        categoryAdapter = CategoryRecyclerViewAdapter(itemList)
        categoryView.adapter = categoryAdapter
        categoryView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)






        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}