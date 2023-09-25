package com.example.bluecodingtube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bluecodingtube.databinding.FragmentDetailPageBinding


class DetailPage : Fragment() {
    private var _binding: FragmentDetailPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        _binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }
}
