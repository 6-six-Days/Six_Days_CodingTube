package com.example.bluecodingtube.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class searchData(val thumbNails: String, val title: String) : Parcelable