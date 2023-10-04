package com.example.bluecodingtube.dataclass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
    data class searchData(
        val thumbNails: String,
        val title: String,
        val info: String,
        val date: String
    ) : Parcelable
