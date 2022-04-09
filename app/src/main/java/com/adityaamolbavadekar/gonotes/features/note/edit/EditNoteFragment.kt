package com.adityaamolbavadekar.gonotes.features.note.edit

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentEditNoteBinding
import com.adityaamolbavadekar.gonotes.features.bottomsheet.EditNoteBottomSheet
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils

class EditNoteFragment : BaseFragment(), Toolbar.OnMenuItemClickListener {

    private lateinit var binding: FragmentEditNoteBinding
    private var noteModel: NoteModel = NoteUtils.Creator().build()
    private lateinit var bottomSheet: EditNoteBottomSheet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.root, "edit_note_transition")
        val noteId: Int = EditNoteFragmentArgs.fromBundle(arguments!!).noteReferenceId
        debugLog("NoteId=$noteId")
        bottomSheet = EditNoteBottomSheet()
        registerObservers(noteId)
        binding.editNoteBottomAppbar.setOnMenuItemClickListener(this)
    }

    private fun registerObservers(noteId: Int) {
        viewModel.getNoteForId(noteId).observe(viewLifecycleOwner, { note ->
            binding.noteTitleEditText.setText(note.title)
            binding.noteBodyEditText.setText(note.body)
        })
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
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_edit_note_share -> {
            }
            R.id.action_edit_note_color -> {
                bottomSheet.show(mContext!!.supportFragmentManager, EditNoteBottomSheet.TAG)
            }
            R.id.action_edit_note_edit_label -> {
            }
            R.id.action_edit_note_attach -> {
            }
            R.id.action_edit_note_text_size_change -> {
                changeTextSize()
            }
            R.id.action_edit_note_duplicate -> {
            }
            R.id.action_edit_note_delete -> {
            }
        }
        return true
    }

    var textSizeChangeRequests = 1

    private fun changeTextSize() {
        if (textSizeChangeRequests > 3) {
            when (textSizeChangeRequests) {
                1 -> {
                    binding.noteTitleEditText.textSize =
                        R.dimen.text_size_incremented_title1.toFloat()
                    binding.noteBodyEditText.textSize =
                        R.dimen.text_size_incremented_body1.toFloat()
                }
                2 -> {
                    binding.noteTitleEditText.textSize =
                        R.dimen.text_size_incremented_title2.toFloat()
                    binding.noteBodyEditText.textSize =
                        R.dimen.text_size_incremented_body2.toFloat()
                }
                3 -> {
                    binding.noteTitleEditText.textSize =
                        R.dimen.text_size_incremented_title3.toFloat()
                    binding.noteBodyEditText.textSize =
                        R.dimen.text_size_incremented_body3.toFloat()
                }
            }
            textSizeChangeRequests += 1
        } else if (textSizeChangeRequests < 3) {
            binding.noteTitleEditText.textSize = R.dimen.text_size_incremented_title0.toFloat()
            binding.noteBodyEditText.textSize = R.dimen.text_size_incremented_body0.toFloat()
            textSizeChangeRequests = 1

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_mark_favourite -> {
                noteModel.isFavourite = true
                if (noteModel.isFavourite) item.title = "Remove from favourites"
                else item.title = "Add to favourites"
                true
            }
            R.id.action_lock_note -> {
                if (item.title == "Unlock") item.title = "Lock"
                else item.title = "Unlock"
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false/*Edit later TODO*/
    override fun setTag(): String = "EditNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user edit an existing note and auto save it to the database."

}