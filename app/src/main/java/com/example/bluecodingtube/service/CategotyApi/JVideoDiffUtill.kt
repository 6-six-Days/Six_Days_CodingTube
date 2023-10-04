package com.example.bluecodingtube.service.CategotyApi

import androidx.recyclerview.widget.DiffUtil
import com.example.bluecodingtube.data.Items


class JVideoDiffUtill(
    private val oldList:List<Items>,
    private val newList:List<Items>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].snippet.title==newList[newItemPosition].snippet.title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldVideo=oldList[oldItemPosition]
        val newVideo=newList[newItemPosition]
        return oldVideo.snippet.title==newVideo.snippet.title
    }
}