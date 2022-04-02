package com.adityaamolbavadekar.gonotes.features.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var binding : FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

}