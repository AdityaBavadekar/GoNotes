package com.adityaamolbavadekar.gonotes.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentBinBinding

class RecycleBinFragment : BaseFragment() {

    private lateinit var binding: FragmentBinBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBinBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false
    override fun setTag(): String = "RecycleBinFragment"
    override fun setDescription(): String = "Fragment class that stores deleted notes for 30-days"

}