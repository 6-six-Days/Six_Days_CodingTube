package com.example.bluecodingtube.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class searchData(
    val imageResourceId : Int,
    val title : String
) :Parcelable