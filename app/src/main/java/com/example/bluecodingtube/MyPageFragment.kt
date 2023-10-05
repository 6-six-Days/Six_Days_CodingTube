package com.example.bluecodingtube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluecodingtube.adapter.LikedItemsAdapter
import com.example.bluecodingtube.databinding.FragmentMyPageBinding
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.util.Util


class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding
    private lateinit var sharedPreferencesManager: Util
    private lateinit var adapter: LikedItemsAdapter

    private val likedItems: MutableList<searchData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        likedItems.addAll(sharedPreferencesManager.getLikedItems())
        adapter = LikedItemsAdapter(likedItems) { position ->
            // 아이템 삭제
            likedItems.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
        binding.rvMyPage.adapter = adapter
        binding.rvMyPage.layoutManager = LinearLayoutManager(requireContext())
    }
}

