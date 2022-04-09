package com.adityaamolbavadekar.gonotes.features.note.create

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.transition.Slide
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentCreateNoteBinding
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils
import com.adityaamolbavadekar.gonotes.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform

class CreateNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransition()
    }

    private fun initTransition() {
        // Set transitions here so we are able to access Fragment's binding views.
        enterTransition = MaterialContainerTransform().apply {
            // Manually add the Views to be shared since this is not a standard Fragment to
            // Fragment shared element transition.
            startView = requireActivity().findViewById(R.id.addNoteButton)
            endView = binding.root
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
            scrimColor = Color.TRANSPARENT/*
            containerColor = requireContext().themeColor(R.attr.colorSurface)
            startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
            endContainerColor = requireContext().themeColor(R.attr.colorSurface)*/
        }
        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
            addTarget(binding.root.id)
        }
    }

    override fun onPause() {
        super.onPause()
        val title = binding.noteTitleEditText.text.toString()
        val body = binding.noteBodyEditText.text.toString()
        if (title.isNotEmpty() && body.isNotEmpty()) {
            val note = NoteUtils.Creator()
                .withTitle(title)
                .withBody(body)
                .build()
            viewModel.insertNote(note)
            Toast.makeText(mContext!!, "Note saved", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false/*Edit later TODO*/
    override fun setTag(): String = "CreateNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user create a new note and auto save it to the database."
}