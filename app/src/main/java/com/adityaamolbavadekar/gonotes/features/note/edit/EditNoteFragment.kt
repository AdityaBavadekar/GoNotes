package com.adityaamolbavadekar.gonotes.features.note.edit

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils

class EditNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentEditNoteBinding
    private var noteModel: NoteModel = NoteUtils.Creator().build()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId: Int = EditNoteFragmentArgs.fromBundle(arguments!!).noteReferenceId
        viewModel.requestNote(noteId)
        viewModel.noteWithId.observe(viewLifecycleOwner) { noteItem ->
            noteModel = noteItem
            binding.noteTitleEditText.setText(noteItem.title)
            binding.noteBodyEditText.setText(noteItem.title)
        }
        viewModel.title.observe(viewLifecycleOwner) {
            binding.noteTitleEditText.setText(it)
        }
        viewModel.body.observe(viewLifecycleOwner) {
            binding.noteBodyEditText.setText(it)
        }
    }

    @Suppress("UselessCallOnNotNull")
    override fun onPause() {
        super.onPause()
        /*Hide keyboard when fragment is navigating*/
        val imm = mContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        try {
            imm.hideSoftInputFromWindow(view?.windowToken,0)
        } catch (e: Exception) {
            debugLog("Failed to hide the Keyboard")
        }
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
    override fun setTag(): String = "EditNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user edit an existing note and auto save it to the database."

}