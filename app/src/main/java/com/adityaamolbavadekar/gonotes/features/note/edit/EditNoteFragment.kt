package com.adityaamolbavadekar.gonotes.features.note.edit

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.colors.toGoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.data.NoteModel
import com.adityaamolbavadekar.gonotes.utils.toast

class EditNoteFragment : Fragment() {


    private lateinit var binding: FragmentEditNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditNoteBinding.inflate(layoutInflater)
        val note : NoteModel = EditNoteFragmentArgs.fromBundle(arguments!!).noteMetadat!!
        binding.noteTitleEditText.setText(note.title)
        binding.noteBodyEditText.setText(note.body)
        return binding.root
    }

    private val editTextActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            menu?.add(0, 100, 0, "Add to new note")
            menu?.add(0, 101, 1, "Make label")
            return true
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            val end = binding.noteBodyEditText.selectionEnd
            val start = binding.noteBodyEditText.selectionStart

            if (end != -1 && start != -1) {
                when (item?.itemId) {
                    100 -> {
                        requireContext().toast("Created a new note")
                    }
                    101 -> {
                        requireContext().toast("Created a new label")
                    }
                    android.R.id.copy -> {
                        requireContext().toast("Copied to clipboard")
                    }
                    android.R.id.selectAll -> {
                        binding.noteBodyEditText.selectAll()
                    }
                    android.R.id.paste -> {
                        requireContext().toast("Copied to clipboard")
                    }
                    android.R.id.pasteAsPlainText -> {
                        requireContext().toast("Copied to clipboard")
                    }
                }
            }
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {

        }
    }

}