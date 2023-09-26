package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.databinding.ActivityBestRecyclerViewBinding
import com.example.bluecodingtube.dataclass.Item

class WordCategoryRecyclerViewAdapter(val Item:MutableList<Item>):RecyclerView.Adapter<WordCategoryRecyclerViewAdapter.ViewHolder>(){
    val Tag="로그"
    private lateinit var binding:ActivityBestRecyclerViewBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordCategoryRecyclerViewAdapter.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=ActivityBestRecyclerViewBinding.inflate(inflater,parent,false)
        val layoutManager = LinearLayoutManager(parent.context, LinearLayoutManager.HORIZONTAL, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WordCategoryRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        val pos=Item[position]
        holder.wordCategoryImage.setImageResource(pos.dummy)
        Log.d(Tag,"WordCategotyRecyclerView Called")
    }

    override fun getItemCount(): Int {
        return Item.size
    }

    inner class ViewHolder(binding: ActivityBestRecyclerViewBinding):RecyclerView.ViewHolder(binding.root) {
        val wordCategoryImage=binding.dummy
    }

}