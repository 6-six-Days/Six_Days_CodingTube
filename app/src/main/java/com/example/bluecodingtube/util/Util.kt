package com.example.bluecodingtube.util

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.bluecodingtube.dataclass.searchData

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Util (private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    private val gson = Gson()

    fun addLikedItem(item:searchData) {
        val likedItems = getLikedItems().toMutableSet()
        likedItems.add(item)
        saveLikedItems(likedItems)
    }

    fun removeLikedItem(item: searchData) {
        val likedItems = getLikedItems().toMutableSet()
        likedItems.remove(item)
        saveLikedItems(likedItems)
    }

    fun getLikedItems(): Set<searchData> {
        val likedItemsJson = sharedPreferences.getString("liked_items", null)
        return if (likedItemsJson != null) {
            val type = object : TypeToken<Set<searchData>>() {}.type
            gson.fromJson(likedItemsJson, type) ?: emptySet()
        } else {
            emptySet()
        }
    }

    private fun saveLikedItems(likedItems: Set<searchData>) {
        val likedItemsJson = gson.toJson(likedItems)
        editor.putString("liked_items", likedItemsJson)
        editor.apply()
    }
    fun isLiked(item: searchData): Boolean {
        val likedItems = getLikedItems()
        return likedItems.contains(item)
    }
}


