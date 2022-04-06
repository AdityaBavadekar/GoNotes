package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.databinding.ItemNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * A [RecyclerView.Adapter] which helps in creating list of notes R.layout.item_notes ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NoteAdapter(private val context: FragmentActivity, private var notes: List<NoteModel>) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    class NoteHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var prefs: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return NoteHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.apply {
            binding.title.text = note.title
            binding.body.text = note.body
            binding.timestamp.text =
                SimpleDateFormat("dd MMM H:mm", Locale.ENGLISH).format(Date(note.created))
/*
            if (note.label.isNotEmpty()) {
                binding.label.text = note.label
                binding.label.isVisible = true
            }

            */
            handleNoteSettings(note, binding)
            ViewCompat.setTransitionName(binding.root, note.id.toString())
            binding.root.setOnClickListener {
                val action =
                    ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(noteMetadata = note)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun handleNoteSettings(note: NoteModel, binding: ItemNoteBinding) {
        /* if (note.isLocked) {
             binding.body.text = context.getString(R.string.note_is_locked)
             binding.body.setTextColor(Color.RED)
             binding.favouriteIcon.isVisible = false
             binding.pinnedIcon.isVisible = false
             binding.label.isVisible = false
             binding.lockedIcon.isVisible = true
             ViewCompat.setTooltipText(binding.lockedIcon, "Locked")
         } else {*/
        if (note.isFavourite) {
            binding.favouriteIcon.isVisible = true
            ViewCompat.setTooltipText(binding.favouriteIcon, "Favourite")
        }
        if (note.isPinned) {
            binding.pinnedIcon.isVisible = true
            ViewCompat.setTooltipText(binding.pinnedIcon, "Pinned")
        }
//        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}
