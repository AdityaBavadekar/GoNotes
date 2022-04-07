package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.app.Activity
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.GoNotes
import com.adityaamolbavadekar.gonotes.databinding.ItemSearchBinding
import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel

class SearchAdapter() :
    RecyclerView.Adapter<SearchAdapter.ItemHolder>() {

    private var searchItems  : MutableList<SearchItemModel> = mutableListOf()

    class ItemHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var prefs: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val suggestion = searchItems[position]
        holder.apply {
            binding.searchTitle.text = suggestion.title
            binding.searchSubtitle.text = suggestion.body
            binding.root.setOnClickListener {
                val action =
                    ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(noteReferenceId = suggestion.suggestedNoteReferenceId)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }


    fun submitList(newSearchItems : MutableList<SearchItemModel>){
        searchItems = newSearchItems
    }

}
