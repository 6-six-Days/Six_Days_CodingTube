package com.example.bluecodingtube

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
                .into(binding.ivDetailThumnail)
        }
//        받아온 타이틀,날짜 연결
        binding.tvDetailTitle.text = testList?.title
        binding.tvDetailDate.text = testList?.date
        binding.tvDetailInfo.text = testList?.info
        //좋아요 클릭시 Myapge로 이동됨
        binding.apply {
            button.setOnClickListener {

                savepref()

            }
        }
    }
    private fun savepref() {
    val sharedPreferences = getSharedPreferences(KEY_Thum, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
//        editor.putInt(KEY_Thum, binding.ivDetailThumnail.여기에 뭐라고 써야하지)
        editor.putString(KEY_Title, binding.tvDetailTitle.text.toString())
        editor.putString(KEY_Date, binding.tvDetailDate.text.toString())
        editor.putString(KEY_Info, binding.tvDetailInfo.text.toString())

        editor.apply()
        Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
    }
    companion object {
        private const val KEY_Thum = "set_thumnail"
        private const val KEY_Title = "set_title"
        private const val KEY_Date = "set_date"
        private const val KEY_Info = "set_info"
    }
}