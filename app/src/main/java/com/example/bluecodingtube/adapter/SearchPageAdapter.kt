package com.example.bluecodingtube.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.SearchItemBinding
import com.example.bluecodingtube.dataclass.searchData


class SearchPageAdapter(private val searchContext: Context) :
    RecyclerView.Adapter<SearchPageAdapter.ItemViewHolder>() {

    var items = ArrayList<searchData>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.title

        Glide.with(searchContext)
            .load(item.thumbNails)
            .into(holder.searchImage)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val searchImage: ImageView = binding.searchImage
        val title: TextView = binding.searchTitle
    }
}



