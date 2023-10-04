package com.example.bluecodingtube.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.DetailpageActivity
import com.example.bluecodingtube.databinding.SearchItemBinding
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.service.YouTubeService

class SearchPageAdapter(private val searchContext: Context) : RecyclerView.Adapter<SearchPageAdapter.ItemViewHolder>() {

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

    inner class ItemViewHolder(binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val searchImage: ImageView = binding.searchImage
        val title: TextView = binding.searchTitle

        init {
            itemView.setOnClickListener(this)// 뷰가 클릭되었을 때 이 클래스의 onClick 메서드가 호출되도록 설정
        }


        override fun onClick(view: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val clickedItem = items[position] // 클릭된 위치에 해당하는 데이터 항목 가져오기




            //리싸이클러뷰중 선택된 항목의 데이터를 가지고 intent 날리기, DetailActivity 호출
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailpageActivity::class.java)
                intent.putExtra("Data", clickedItem)
                itemView.context.startActivity(intent)
            }

        }
    }
}