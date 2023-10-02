package com.example.bluecodingtube.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.SearchItemBinding
import com.example.bluecodingtube.dataclass.searchData

class SearchPageAdapter(searchContext : Context) : RecyclerView.Adapter<SearchPageAdapter.searchItemViewHolder>(){

    var searchItem = ArrayList<searchData>()


    override fun getItemCount()= searchItem.size

    fun clearItem() {
        searchItem.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: SearchPageAdapter.searchItemViewHolder, position: Int) {
        val item = searchItem[position]
        holder.title.text = item.title


        val searchImage = searchItem[position].thumbNails
        // 영상 썸네일 세팅
        Glide.with(holder.searchImage)
            .load(item.thumbNails)
            .into(holder.searchImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPageAdapter.searchItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return searchItemViewHolder(binding)
    }

    inner class searchItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root){
        val searchImage : ImageView = binding.searchImage
        val title : TextView = binding.searchTitle
    }





}