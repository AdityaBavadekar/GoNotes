package com.adityaamolbavadekar.gonotes.features.note.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentCreateNoteBinding
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils

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
            Toast.makeText(mContext!!,"Note saved",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false/*Edit later TODO*/
    override fun setTag(): String = "CreateNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user create a new note and auto save it to the database."
}