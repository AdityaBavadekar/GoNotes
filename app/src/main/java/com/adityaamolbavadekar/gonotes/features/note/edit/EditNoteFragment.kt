package com.adityaamolbavadekar.gonotes.features.note.edit

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.transition.Slide
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.utils.snack
import com.google.android.material.transition.MaterialContainerTransform

class EditNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private lateinit var noteModel: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditNoteBinding.inflate(layoutInflater)
        noteModel = EditNoteFragmentArgs.fromBundle(arguments!!).noteMetadata!!
        binding.noteTitleEditText.setText(noteModel.title)
        binding.noteBodyEditText.setText(noteModel.body)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterTransition = MaterialContainerTransform().apply {
            startViewId = R.id.addNoteButton
            endViewId = R.id.coordinator
            drawingViewId = R.id.fragmentHolder
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
            scrimColor = Color.TRANSPARENT
        }

        returnTransition = Slide().apply {
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
            addTarget(R.id.coordinator)
        }

    }

    override fun onPause() {
        super.onPause()
        val titleText = binding.noteTitleEditText.text.toString()
        val bodyText = binding.noteBodyEditText.text.toString()
        if (titleText.isNotEmpty() && bodyText.isNotEmpty() && titleText != noteModel.title && bodyText != noteModel.title) {
            noteModel.title = titleText
            noteModel.body = bodyText
            viewModel.updateNote(noteModel)
            binding.root.snack("Note updated")
        }
    }

    override fun onWelcomeNeeded() {

    }

    override fun onDebug() {}
    override fun setTag(): String = "EditNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user edit an existing note and auto save it to the database."

}