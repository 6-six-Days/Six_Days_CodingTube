package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.databinding.ActivityBestRecyclerViewBinding
import com.example.bluecodingtube.service.bestApi.VideoDiffUtill
import com.example.bluecodingtube.viewModel.BestViewModel
import io.opencensus.tags.Tag


class BestRecyclerViewAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     val Tag="로그"



    private var oldItems= emptyList<PlayList>()
    class itemHolder(val binding: ActivityBestRecyclerViewBinding):RecyclerView.ViewHolder(binding.root){



        fun setdata(data: PlayList){
            Log.d("respone","${data.toString()}")
            binding.title.text=data.snippet.title
            binding.id.text=data.snippet.channelId
            binding.viewCount.text=data.snippet.publishedAt
            Glide.with(binding.root).load(data.snippet.thumbnails.medium.url)
                .into(binding.thumbnail)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=ActivityBestRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return itemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as itemHolder).setdata(oldItems[position])
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<PlayList>?) {
        if (newList != null) {
            val videoDiff = VideoDiffUtill(oldItems, newList)
            val diff = DiffUtil.calculateDiff(videoDiff)
            oldItems = newList
            diff.dispatchUpdatesTo(this)
        }
    }



}
