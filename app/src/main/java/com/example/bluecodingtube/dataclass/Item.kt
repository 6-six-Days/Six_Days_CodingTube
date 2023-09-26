package com.example.bluecodingtube.dataclass

import com.example.bluecodingtube.R

data class Item(

    val dummy: Int
)

object dummydata{
    fun dummyList():MutableList<Item> {
        val datalist = mutableListOf<Item>()

        datalist.add(
            Item(R.drawable.star)
        )
        datalist.add(
            Item(R.drawable.star)
        )
        datalist.add(
            Item(R.drawable.star)
        )
        datalist.add(
            Item(R.drawable.star)
        )
        datalist.add(
            Item(R.drawable.star)
        )
        datalist.add(
            Item(R.drawable.star)
        )

        return datalist
    }

}