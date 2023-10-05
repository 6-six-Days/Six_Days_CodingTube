package com.example.bluecodingtube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.databinding.LikeItemBinding

import com.bumptech.glide.Glide // Glide 라이브러리를 import
import com.example.bluecodingtube.dataclass.searchData

class LikedItemsAdapter(
    private val items: MutableList<searchData>,
    private val itemClickCallback: (position: Int) -> Unit
) : RecyclerView.Adapter<LikedItemsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: LikeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: searchData, itemClickCallback: (position: Int) -> Unit) {
            binding.likeTitle.text = item.title


            // Glide를 사용하여 섬네일 이미지 로딩
            Glide.with(binding.root)
                .load(item.thumbNails)
                .into(binding.likeImage)

            binding.rvitem.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickCallback(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LikeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, itemClickCallback)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
