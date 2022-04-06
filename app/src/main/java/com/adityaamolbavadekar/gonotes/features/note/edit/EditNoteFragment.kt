package com.adityaamolbavadekar.gonotes.features.note.edit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.transition.Slide
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils
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
        noteModel = EditNoteFragmentArgs.fromBundle(arguments!!).noteMetadata
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

    @Suppress("UselessCallOnNotNull")
    override fun onPause() {
        super.onPause()
        val titleText = binding.noteTitleEditText.text.toString()
        val bodyText = binding.noteBodyEditText.text.toString()

        /*Check if Title and Body is not empty or does not contain only spaces.*/
        if (titleText.trim().isNullOrEmpty() && bodyText.trim().isNullOrEmpty()) {
            Toast.makeText(mContext!!, "Empty note discarded", Toast.LENGTH_SHORT).show()
        }
        /*Check if Title and Body is not same as earlier note's Title and Body.*/
        else if (titleText != noteModel.title && bodyText != noteModel.title) {
            val noteItem = NoteUtils.Updater()
                .withTitle(titleText)
                .withBody(bodyText)
                .build(noteModel)
            viewModel.updateNote(noteItem)
            Toast.makeText(mContext!!, "Note saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onWelcomeNeeded() {

    }
    override fun setHasMenu(): Boolean = false/*Edit later TODO*/
    override fun onDebug() {}
    override fun setTag(): String = "EditNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user edit an existing note and auto save it to the database."

}