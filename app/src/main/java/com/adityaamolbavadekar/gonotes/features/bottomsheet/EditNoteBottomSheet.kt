package com.adityaamolbavadekar.gonotes.features.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityaamolbavadekar.gonotes.databinding.EditNoteBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditNoteBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: EditNoteBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditNoteBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "EditNoteBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            val type =SimpleBottomSheetMenuAdapter.BottomSheetMenu.COLORS.name
            adapter = SimpleBottomSheetMenuAdapter(type)
        }
    }

}