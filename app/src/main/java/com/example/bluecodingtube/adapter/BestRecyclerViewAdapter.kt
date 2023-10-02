package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.data.Items
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.data.YoutubeVideoInfo
import com.example.bluecodingtube.databinding.ActivityBestRecyclerViewBinding
import com.example.bluecodingtube.dataclass.Item
import com.example.bluecodingtube.service.RetrofitClient
import com.example.bluecodingtube.service.bestApi.VideoDiffUtill
import com.example.bluecodingtube.viewModel.BestViewModel


class BestRecyclerViewAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     val Tag="로그"
    private var oldItems= emptyList<PlayList>()



    class itemHolder(itemView: ActivityBestRecyclerViewBinding):RecyclerView.ViewHolder(itemView.root){
        private val binding=itemView
        fun setdata(data: PlayList){
            binding.title.text=data.snippet.title
            binding.id.text=data.snippet.channelId
            binding.viewCount.text=data.snippet.publishedAt
            Glide.with(binding.root).load(data.snippet.thumbnails.medium.url)
               .into(binding.thumbnail)
    Log.d("데이터","${data.toString()}")
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view=ActivityBestRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false
        )
        return itemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as itemHolder).setdata(oldItems[position])
    }

    override fun getItemCount(): Int {
        return oldItems.size
    }

    fun setData(newList: List<PlayList>){
        val videoDiff= VideoDiffUtill(oldItems,newList)
        val diff=DiffUtil.calculateDiff(videoDiff)
        oldItems=newList
        diff.dispatchUpdatesTo(this)

    }


}
