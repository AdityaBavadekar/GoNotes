package com.adityaamolbavadekar.gonotes.features.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentLauncherBinding

class LauncherFragment : Fragment() {

    private lateinit var binding: FragmentLauncherBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(layoutInflater)
        return binding.root
    }
}