package com.example.bluecodingtube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
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
        Log.d("recievedata","gotodetail")
        //Glide를 사용하여 이미지뷰에 받아온 썸네일URI연결
        testList?.thumbNails.let { imageUrI ->
            Glide.with(binding.root)
                .load(imageUrI)
                .into(binding.imageView4)
        }
        //받아온 타이틀,날짜 연결
        binding.tvDetailTitle.text = testList?.title
        binding.tvDetailDate.text = testList?.date
        //좋아요 클릭시 Myapge로 이동됨
        binding.apply {
            button.setOnClickListener {
                setFragment(MyPage())
                Log.d("Click btn_like","gogogogogogogo")
            }
        }
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.DetailLayout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }
}