package com.example.bluecodingtube.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.DetailpageActivity
import com.example.bluecodingtube.databinding.SearchItemBinding
import com.example.bluecodingtube.service.YouTubeService

class SearchPageAdapter(private val searchContext: List<searchData>) : RecyclerView.Adapter<SearchPageAdapter.searchItemViewHolder>() {

    val items = ArrayList<searchData>()

    init {
        items.addAll(searchContext)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: SearchPageAdapter.searchItemViewHolder, position: Int) {

        val item = items[position]
        holder.title.text = item.title
        holder.searchImage.setImageResource(item.imageResourceId)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchPageAdapter.searchItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return searchItemViewHolder(binding)
    }

    inner class searchItemViewHolder(binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val searchImage: ImageView = binding.searchImage
        val title: TextView = binding.searchTitle

        override fun onClick(view: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val clickedItem = items[position] // 클릭된 위치에 해당하는 데이터 항목 가져오기

            //리싸이클러뷰중 선택된 항목의 데이터를 가지고 intent 날리기, DetailActivity 호출
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailpageActivity::class.java)
                intent.putExtra("Data", searchData[adapterPosition])
                itemView.context.startActivity(intent)
            }

        }

    }
}