package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.databinding.ActivityBestRecyclerViewBinding
import com.example.bluecodingtube.dataclass.Item


class BestRecyclerViewAdapter(val Item:MutableList<Item>) :RecyclerView.Adapter<BestRecyclerViewAdapter.ViewHolder>() {

     val Tag="로그"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestRecyclerViewAdapter.ViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding= ActivityBestRecyclerViewBinding.inflate(inflater,parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val pos=Item[position]
        holder.bestImage.setImageResource(pos.dummy)
        Log.d(Tag,"BestRecyclerViewAdapter Called")
    }

    override fun getItemCount(): Int {
        return Item.size
    }

    class ViewHolder(binding: ActivityBestRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {

        val bestImage = binding.dummy
    }

}