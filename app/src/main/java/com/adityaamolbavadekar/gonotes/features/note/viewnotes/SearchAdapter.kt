package com.adityaamolbavadekar.gonotes.features.note.view

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.databinding.ItemSearchBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.utils.NavigationUtils
import com.adityaamolbavadekar.gonotes.utils.toast

class SearchAdapter(
    private val context: FragmentActivity,
    private val searchItems: MutableList<NoteModel>
) :
    RecyclerView.Adapter<SearchAdapter.ItemHolder>() {

    class ItemHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var prefs: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return ItemHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val note = searchItems[position]
        holder.apply {
            binding.searchTitle.text = note.title
            binding.searchSubtitle.text = note.body
            binding.root.setOnClickListener {
                context.toast(note.id.toString())
            }
            binding.root.setOnClickListener {
                val action =
                    ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(noteMetadat = note)
                NavigationUtils.toEditNote(it, action)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }
}
