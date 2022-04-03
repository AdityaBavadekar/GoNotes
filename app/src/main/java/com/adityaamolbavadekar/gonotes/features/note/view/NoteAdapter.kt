package com.adityaamolbavadekar.gonotes.features.note.view

import android.content.SharedPreferences
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.ItemNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.data.NoteModel
import com.adityaamolbavadekar.gonotes.utils.NavigationUtils

/**
 *
 * A [RecyclerView.Adapter] which helps in creating list of notes R.layout.item_notes ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NoteAdapter(private val context:FragmentActivity,private var notes : List<NoteModel>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    class NoteHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var prefs : SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return NoteHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val note = notes[position]
        holder.apply {
            val r = prefs.getString("CardRadius","15")?: "15"
            binding.root.radius = r.toFloat()
            binding.title.text = note.title
            binding.body.text = note.body
            binding.timestamp.text = note.createdGeneralForm
            handleNoteSettings(note,binding)
        }
    }

    private fun handleNoteSettings(note: NoteModel, binding: ItemNoteBinding) {
        if(note.isLocked){
            binding.body.text = context.getString(R.string.note_is_locked)
            binding.body.setTextColor(Color.RED)
            binding.favouriteIcon.isVisible = false
            binding.pinnedIcon.isVisible = false
            binding.label.isVisible = false
            binding.lockedIcon.isVisible = true
            ViewCompat.setTooltipText(binding.lockedIcon,"Locked")
        }else{
            if (note.isFavourite){
                binding.favouriteIcon.isVisible = true
                ViewCompat.setTooltipText(binding.favouriteIcon,"Favourite")
            }
            if (note.isPinned){
                binding.pinnedIcon.isVisible = true
                ViewCompat.setTooltipText(binding.pinnedIcon,"Pinned")
            }
            if(note.label.isNotEmpty()){
                binding.label.text = note.label
                binding.label.isVisible = true
            }
        }

        binding.root.setOnClickListener {
            val action = ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(noteMetadat = note)
            NavigationUtils.toEditNote(it,action)
        }

    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
