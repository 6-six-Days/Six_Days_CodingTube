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
import com.bumptech.glide.Glide
import com.example.bluecodingtube.adapter.BestRecyclerViewAdapter
import com.example.bluecodingtube.adapter.MusicRecyclerViewAdapet
import com.example.bluecodingtube.adapter.TodayRecyclerViewAdapter
import com.example.bluecodingtube.adapter.wordCategoryRecyclerView
import com.example.bluecodingtube.data.Items
import com.example.bluecodingtube.databinding.FragmentHomePageBinding
import com.example.bluecodingtube.viewModel.BestViewModel
import com.example.bluecodingtube.viewModel.CCategoryViewModel
import com.example.bluecodingtube.viewModel.JCategoryViewModel
import com.example.bluecodingtube.viewModel.KCategoryViewModel
import com.example.bluecodingtube.viewModel.MusicViewModel
import com.example.bluecodingtube.viewModel.PCategoryViewModel
import com.example.bluecodingtube.viewModel.TodayViewModel


class HomePage : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    private lateinit var bestAdapter: BestRecyclerViewAdapter
    private lateinit var wordCategoryAdapter: wordCategoryRecyclerView
    private lateinit var musicAdapter: MusicRecyclerViewAdapet
    private lateinit var todayAdapter: TodayRecyclerViewAdapter
    private var bestViewModel: BestViewModel? =null
    private var jCategoryViewModel:JCategoryViewModel? =null
    private var cCategoryViewModel: CCategoryViewModel? =null
    private var kCategoryViewModel:KCategoryViewModel? =null
    private var pCategoryViewModel:PCategoryViewModel? =null
    private var musicViewModel:MusicViewModel? =null
    private var todayViewModel:TodayViewModel?= null






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)

        //인기동영상
        bestAdapter=BestRecyclerViewAdapter()
        binding.BestRecyclerView.adapter=bestAdapter
        binding.BestRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bestViewModel = ViewModelProvider(this).get(BestViewModel::class.java)
        bestViewModel?.video?.observe(viewLifecycleOwner) {
            if (it != null) {
                bestAdapter.setData(it.items)


            }
        }

        wordCategoryAdapter = wordCategoryRecyclerView()
        binding.worldcategoryRecyclerView.adapter=wordCategoryAdapter
        binding.worldcategoryRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        //c언어검색
        cCategoryViewModel=ViewModelProvider(this).get(CCategoryViewModel::class.java)
        cCategoryViewModel?.cvideo?.observe(viewLifecycleOwner){
            if (it!=null){
                wordCategoryAdapter.setData(it.items)
            }
        }
        //java언어검색
        jCategoryViewModel=ViewModelProvider(this).get(JCategoryViewModel::class.java)
        jCategoryViewModel?.jvideo?.observe(viewLifecycleOwner){
            if (it!=null){
                wordCategoryAdapter.setData(it.items)
            }
        }
        //코틀린 검색
        kCategoryViewModel=ViewModelProvider(this).get(KCategoryViewModel::class.java)
        kCategoryViewModel?.kvideo?.observe(viewLifecycleOwner){
            if (it!=null){
                wordCategoryAdapter.setData(it.items)
            }
        }

        //파이썬
        pCategoryViewModel=ViewModelProvider(this).get(PCategoryViewModel::class.java)
        pCategoryViewModel?.pvideo?.observe(viewLifecycleOwner){
            if (it!=null){

                wordCategoryAdapter.setData(it.items)


            }
        }

        //코딩음악

        musicAdapter=MusicRecyclerViewAdapet()
        binding.MusicRecyclerView.adapter=musicAdapter
        binding.MusicRecyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        musicViewModel = ViewModelProvider(this).get(MusicViewModel::class.java)
        musicViewModel?.mvideo?.observe(viewLifecycleOwner) {
            if (it != null) {
                musicAdapter.setData(it.items)


            }
        }

        todayAdapter= TodayRecyclerViewAdapter()
        binding.TodayYoutuberRecylerView.adapter=todayAdapter
        binding.TodayYoutuberRecylerView.layoutManager= LinearLayoutManager(requireContext())
        todayViewModel = ViewModelProvider(this).get(TodayViewModel::class.java)
        todayViewModel?.tvideo?.observe(viewLifecycleOwner) {
            if (it != null) {
                todayAdapter.setData(it.items)


            }
        }




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
                val selectedValue = dataType[position]


                when (selectedValue) {
                    "C" -> {
                        wordCategoryAdapter.setData(cCategoryViewModel?.cvideo?.value?.items)
                    }
                    "JAVA" -> {
                        wordCategoryAdapter.setData(jCategoryViewModel?.jvideo?.value?.items)
                    }
                    "KOTLIN"->{
                        wordCategoryAdapter.setData(kCategoryViewModel?.kvideo?.value?.items)
                    }
                    "PYTHON"->{
                        wordCategoryAdapter.setData(pCategoryViewModel?.pvideo?.value?.items)
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }






        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}