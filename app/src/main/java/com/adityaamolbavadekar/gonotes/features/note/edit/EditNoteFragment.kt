package com.adityaamolbavadekar.gonotes.features.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {


    private lateinit var binding : FragmentEditNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditNoteBinding.inflate(layoutInflater)
        return binding.root
    }

}