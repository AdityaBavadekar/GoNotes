package com.adityaamolbavadekar.gonotes.features.note.create

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentCreateNoteBinding
import com.adityaamolbavadekar.gonotes.utils.InfoCardView
import com.adityaamolbavadekar.gonotes.utils.snack
import com.google.android.material.transition.MaterialContainerTransform

class CreateNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentHolder
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        ViewCompat.setTransitionName(binding.root,getString(R.string.transition_name_create_note))
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        val title = binding.noteTitleEditText.text.toString()
        val body = binding.noteBodyEditText.text.toString()
        if (title.isNotEmpty()&&body.isNotEmpty()){
            viewModel.createSimpleNote(title, body)
            binding.root.snack("Note saved")
        }
    }

    override fun onWelcomeNeeded() {
        val view = InfoCardView.Builder(mContext!!)
            .withMessage("This is a Welcome from Go Notes.")
            .withTitle("Welcome")
            .build()
        try {
            mContext!!.actionBar?.customView = view
        } catch (e: Exception) {
        }
    }

    override fun onDebug() {
        //TODO("Not yet implemented")
    }

    override fun setTag(): String = "CreateNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which helps user create a new note and auto save it to the database."

}