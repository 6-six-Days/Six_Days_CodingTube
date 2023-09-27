package com.example.bluecodingtube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.databinding.SearchItemBinding

class SearchPageAdapter(private val searchContext: List<searchData>) : RecyclerView.Adapter<SearchPageAdapter.searchItemViewHolder>(){

    val items = ArrayList<searchData>()

    init {
        items.addAll(searchContext)
    }
    override fun getItemCount()= items.size


    override fun onBindViewHolder(holder: SearchPageAdapter.searchItemViewHolder, position: Int) {

        val item = items[position]
        holder.title.text = item.title
        holder.searchImage.setImageResource(item.imageResourceId)
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