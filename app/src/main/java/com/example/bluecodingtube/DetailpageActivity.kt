package com.example.bluecodingtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bluecodingtube.adapter.searchData
import com.example.bluecodingtube.databinding.ActivityDetailpageBinding

class DetailpageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailpage)

        var testList = intent.getParcelableExtra<searchData>("Data") // parcelable 데이터를 받아올때 사용하는 함수
    }

}