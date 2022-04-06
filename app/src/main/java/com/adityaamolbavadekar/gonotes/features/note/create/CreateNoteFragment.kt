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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentHolder
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
            scrimColor = Color.TRANSPARENT
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateNoteBinding.inflate(layoutInflater)
//        ViewCompat.setTransitionName(binding.root,getString(R.string.transition_name_create_note))
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
    override fun onDebug() {}
    override fun setTag(): String = "CreateNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user create a new note and auto save it to the database."
}