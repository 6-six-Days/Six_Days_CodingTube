package com.example.bluecodingtube

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.ActivityDetailpageBinding
import com.example.bluecodingtube.dataclass.YouTubeVideoItem
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.util.Util

class DetailpageActivity : AppCompatActivity() {

    //    val binding by lazy { ActivityDetailpageBinding.inflate(layoutInflater) }
//    var items = ArrayList<searchData>()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        setContentView(binding.root)
//
//        var testList = intent.getParcelableExtra<searchData>("Data") // parcelable 데이터를 받아올때 사용하는 함수
//        Log.d("recievedata","gotodetail")
//        //Glide를 사용하여 이미지뷰에 받아온 썸네일URI연결
//        testList?.thumbNails.let { imageUrI ->
//            Glide.with(binding.root)
//                .load(imageUrI)
//                .into(binding.ivDetailThumnail)
//        }
////        받아온 타이틀,날짜 연결
//        binding.tvDetailTitle.text = testList?.title
//        binding.tvDetailDate.text = testList?.date
//        binding.tvDetailInfo.text = testList?.info
//        //좋아요 클릭시 Myapge로 이동됨
//        binding.apply {
//            button.setOnClickListener {
//
//                savepref()
//
//            }
//        }
//    }
//    private fun savepref() {
//    val sharedPreferences = getSharedPreferences(KEY_Thum, Context.MODE_PRIVATE)
//    val editor = sharedPreferences.edit()
////        editor.putInt(KEY_Thum, binding.ivDetailThumnail.여기에 뭐라고 써야하지)
//        editor.putString(KEY_Title, binding.tvDetailTitle.text.toString())
//        editor.putString(KEY_Date, binding.tvDetailDate.text.toString())
//        editor.putString(KEY_Info, binding.tvDetailInfo.text.toString())
//
//        editor.apply()
//        Toast.makeText(applicationContext, "Saved!", Toast.LENGTH_SHORT).show()
//    }
//    companion object {
//        private const val KEY_Thum = "set_thumnail"
//        private const val KEY_Title = "set_title"
//        private const val KEY_Date = "set_date"
//        private const val KEY_Info = "set_info"
//    }
//}
    private lateinit var binding: ActivityDetailpageBinding
    private lateinit var sharedPreferencesManager: Util

    private lateinit var videoItem: searchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailpageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        setupViews()
    }

    private fun setupViews() {
        var testList = intent.getParcelableExtra<searchData>("Data")
        // videoItem에 대한 정보를 뷰바인딩을 통해 화면에 표시하는 코드 (제목, 설명, 섬네일 등)
        binding.tvDetailTitle.text = testList?.title
        binding.tvDetailInfo.text = testList?.info
        binding.tvDetailDate.text = testList?.date

        // 섬네일 이미지 로딩 라이브러리(Glide)를 사용하여 섬네일을 표시
        Glide.with(this)
            .load(testList?.thumbNails)
            .into(binding.ivDetailThumnail)

        // 좋아요 버튼 초기 상태 설정
        val isLiked = sharedPreferencesManager.isLiked(videoItem)
        updateLikeButtonState(isLiked)

        // 좋아요 버튼 클릭 이벤트 핸들러
        binding.button.setOnClickListener {
            if (isLiked) {
                // 이미 좋아요한 동영상인 경우 좋아요 취소
                sharedPreferencesManager.removeLikedItem(
                    searchData(
                        videoItem.title,
                        videoItem.info,
                        videoItem.date,
                        videoItem.thumbNails
                    )
                )
                updateLikeButtonState(false)
            } else {
                // 좋아요하지 않은 동영상인 경우 좋아요 추가
                sharedPreferencesManager.addLikedItem(
                    searchData(

                        videoItem.title,
                        videoItem.info,
                        videoItem.date,
                        videoItem.thumbNails
                    )
                )
                updateLikeButtonState(true)
            }
        }
    }

    private fun updateLikeButtonState(isLiked: Boolean) {
        if (isLiked) {
            binding.button.text = "좋아요 취소"
        } else {
            binding.button.text = "좋아요"
        }
    }
}

