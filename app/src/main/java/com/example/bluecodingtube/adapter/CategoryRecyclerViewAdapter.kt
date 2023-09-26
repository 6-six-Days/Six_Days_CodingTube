package com.example.bluecodingtube.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluecodingtube.databinding.ActivityCategoryRecyclerViewBinding
import com.example.bluecodingtube.dataclass.Item

class CategoryRecyclerViewAdapter(val Item:MutableList<Item>):RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>(){
    val Tag="로그"
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityCategoryRecyclerViewBinding.inflate(inflater,parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val pos=Item[position]
        holder.categotyImage.setImageResource(pos.dummy)
        Log.d(Tag,"CategoryRecyclerViewAdapater Called")
    }

    override fun getItemCount(): Int {
        return Item.size
    }

    class ViewHolder(val binding:ActivityCategoryRecyclerViewBinding):RecyclerView.ViewHolder(binding.root){
        val categotyImage=binding.dummy
    }
}