package com.example.bluecodingtube.service.bestApi

import androidx.recyclerview.widget.DiffUtil
import com.example.bluecodingtube.data.PlayList
import com.example.bluecodingtube.data.Snippet
import com.example.bluecodingtube.data.YoutubeVideo
import com.example.bluecodingtube.data.YoutubeVideoInfo

class VideoDiffUtill(
    private val oldList:List<PlayList>,
    private val newList:List<PlayList>
) :DiffUtil.Callback(){

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