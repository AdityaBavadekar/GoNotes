package com.adityaamolbavadekar.gonotes.features.bin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentBinBinding

class BinFragment : Fragment() {

    private lateinit var binding : FragmentBinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBinBinding.inflate(layoutInflater)
        return binding.root
    }

}