package com.example.bluecodingtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.ActivityDetailpageBinding
import com.example.bluecodingtube.dataclass.searchData

class DetailpageActivity : AppCompatActivity() {

    val binding by lazy { ActivityDetailpageBinding.inflate(layoutInflater) }
    var items = ArrayList<searchData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(binding.root)

        var testList = intent.getParcelableExtra<searchData>("Data") // parcelable 데이터를 받아올때 사용하는 함수
        testList?.thumbNails.let { imageUrI ->
            Glide.with(binding.root)
                .load(imageUrI)
                .into(binding.imageView4)
        }
        binding.tvDetailTitle.text = testList?.title
        binding.tvDetailDate.text = testList?.date

    }

}