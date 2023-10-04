package com.example.bluecodingtube.dataclass

import android.os.Parcelable
import com.example.bluecodingtube.viewModel.SearchItemModel
import kotlinx.parcelize.Parcelize

@Parcelize
    data class searchData(
        val thumbNails: String,
        val title: String,
        val info: String,
        val date: String
    ) : Parcelable {
    fun toSearchItemModel(): SearchItemModel {
        return SearchItemModel(thumbNails, title,)
    }
}