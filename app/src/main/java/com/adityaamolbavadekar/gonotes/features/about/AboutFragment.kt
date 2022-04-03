package com.adityaamolbavadekar.gonotes.features.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding : FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAboutBinding.inflate(layoutInflater)
        return binding.root
    }

}