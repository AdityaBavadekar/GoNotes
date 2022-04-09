package com.adityaamolbavadekar.gonotes.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentBinBinding
import com.adityaamolbavadekar.gonotes.databinding.FragmentViewNotesBinding

class FavouritesFragment : BaseFragment(){

    private lateinit var binding: FragmentViewNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false
    override fun setTag(): String = "FavouritesFragment"
    override fun setDescription(): String = "Fragment class that stores favourite notes"
}