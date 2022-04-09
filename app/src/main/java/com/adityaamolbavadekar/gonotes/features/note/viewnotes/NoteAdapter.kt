package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
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
class NoteAdapter(
    private val context: FragmentActivity,
    private var notes: List<NoteModel>,
    private val noteAdapterListener: NoteAdapterListener
) :
    RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    interface NoteAdapterListener {
        fun onNoteClicked(cardView: View, note: NoteModel)
        fun onNoteLongPressed(binding: ItemNoteBinding, note: NoteModel): Boolean
    }

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
                SimpleDateFormat("dd MMM h:mm", Locale.ENGLISH).format(Date(note.created))
/*
            if (note.label.isNotEmpty()) {
                binding.label.text = note.label
                binding.label.isVisible = true
            }

            */
            handleNoteSettings(note, binding)
            ViewCompat.setTransitionName(binding.root, note.id.toString())
            binding.root.setOnClickListener {cardView->
                noteAdapterListener.onNoteClicked(cardView, note)
            }
            binding.root.setOnLongClickListener {
                noteAdapterListener.onNoteLongPressed(
                    binding,
                    note
                )
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
