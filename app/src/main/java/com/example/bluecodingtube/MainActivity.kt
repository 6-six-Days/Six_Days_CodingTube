package com.example.bluecodingtube

import SearchPage
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.bluecodingtube.databinding.ActivityMainBinding
import com.example.codingtube.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        var viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(HomePage())
        viewPagerAdapter.addFragment(SearchPage())
        viewPagerAdapter.addFragment(MyPage())


        binding.mainView.apply {
            adapter = viewPagerAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            })
        }
        val iconList = ArrayList<Drawable?>()
        iconList.add(ContextCompat.getDrawable(this, R.drawable.baseline_home_24))
        iconList.add(ContextCompat.getDrawable(this, R.drawable.baseline_search_24))
        iconList.add(ContextCompat.getDrawable(this, R.drawable.baseline_toc_24))

        TabLayoutMediator(binding.tlNavigationView, binding.mainView) { tab, position ->
            Log.e("YMC", "ViewPager position: $position")
            tab.icon = iconList[position]

        }.attach()
    }
}