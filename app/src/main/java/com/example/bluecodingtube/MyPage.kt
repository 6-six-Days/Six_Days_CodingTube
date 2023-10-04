package com.example.bluecodingtube

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bluecodingtube.adapter.MyPageAdapter
import com.example.bluecodingtube.databinding.FragmentMyPageBinding



class MyPage : Fragment() {
    private lateinit var mContext: Context
    private var binding: FragmentMyPageBinding? = null
    private lateinit var adapter: MyPageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        likedItem = mainActivity.likedItems

        adapter = MyPageAdapter(mContext).apply {
            items = likedItem.toMutableList()
        }
        binding = FragmentMyPageBinding.inflate(inflater, container, false).apply {
            rvMyPage.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvMyPage.adapter = adapter
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

