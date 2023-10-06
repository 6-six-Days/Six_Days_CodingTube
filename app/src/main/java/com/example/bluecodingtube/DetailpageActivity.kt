package com.example.bluecodingtube

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.ActivityDetailpageBinding
import com.example.bluecodingtube.dataclass.searchData
import com.example.bluecodingtube.util.Util

class DetailpageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailpageBinding
    private lateinit var sharedPreferencesManager: Util
    private lateinit var videoItem: searchData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferencesManager = Util(this)
        binding = ActivityDetailpageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Intent에서 videoItem 데이터 받기
        videoItem = intent.getParcelableExtra<searchData>("Data")!!
        setupViews()
    }
    private fun setupViews() {
        // videoItem에 대한 정보를 뷰바인딩을 통해 화면에 표시하는 코드 (제목, 설명, 섬네일 등)
        binding.tvDetailTitle.text = videoItem.title
        binding.tvDetailInfo.text = videoItem.info
        binding.tvDetailDate.text = videoItem.date
        // 섬네일 이미지 로딩 라이브러리(Glide)를 사용하여 섬네일을 표시
        Glide.with(this)
            .load(videoItem.thumbNails)
            .into(binding.ivDetailThumnail)
        // 좋아요 버튼 초기 상태 설정
        val isLiked = sharedPreferencesManager.isLiked(videoItem)
        updateLikeButtonState(isLiked)
        // 좋아요 버튼 클릭 이벤트 핸들러
        binding.button.setOnClickListener {
            if (isLiked) {
                // 이미 좋아요한 동영상인 경우 좋아요 취소
                sharedPreferencesManager.removeLikedItem(videoItem)
                updateLikeButtonState(false)
            } else {
                // 좋아요하지 않은 동영상인 경우 좋아요 추가
                sharedPreferencesManager.addLikedItem(videoItem)
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

