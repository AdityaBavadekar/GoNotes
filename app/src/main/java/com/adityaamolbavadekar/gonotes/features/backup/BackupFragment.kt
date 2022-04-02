package com.adityaamolbavadekar.gonotes.features.backup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentBackupBinding

class BackupFragment : Fragment() {

    private lateinit var binding : FragmentBackupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBackupBinding.inflate(layoutInflater)
        return binding.root
    }
}