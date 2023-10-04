package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.data.Items
import com.example.bluecodingtube.databinding.ActivityBestRecyclerViewBinding
import com.example.bluecodingtube.databinding.ActivityTodayYoutuberBinding
import com.example.bluecodingtube.service.bestApi.VideoDiffUtill

class TodayRecyclerViewAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    val Tag="로그"



    private var oldItems= emptyList<Items>()
    class itemHolder(val binding: ActivityTodayYoutuberBinding): RecyclerView.ViewHolder(binding.root){



        fun setdata(data: Items){
            binding.id.text=data.snippet.channelTitle
            Glide.with(binding.root).load(data.snippet.thumbnails.medium.url)
                .into(binding.thumbnail)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=
            ActivityTodayYoutuberBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return itemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as itemHolder).setdata(oldItems[position])
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<Items>?) {
        if (newList != null) {
            val videoDiff = VideoDiffUtill(oldItems, newList)
            val diff = DiffUtil.calculateDiff(videoDiff)
            oldItems = newList
            diff.dispatchUpdatesTo(this)
        }
    }



}