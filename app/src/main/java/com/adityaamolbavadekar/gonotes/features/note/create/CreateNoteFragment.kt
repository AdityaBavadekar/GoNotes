package com.adityaamolbavadekar.gonotes.features.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {

    private lateinit var binding : FragmentCreateNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

}