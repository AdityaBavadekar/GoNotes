package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.ItemSearchBinding
import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel
import com.google.android.material.snackbar.Snackbar

class SearchAdapter(
    private val searchSuggestionListener: SearchSuggestionListener,
    var searchItems: MutableList<SearchItemModel> = mutableListOf()
) :
    RecyclerView.Adapter<SearchAdapter.ItemHolder>() {

    interface SearchSuggestionListener {
        fun onSuggestionClicked(binding: ItemSearchBinding, suggestion: SearchItemModel)
        fun onSuggestionLongClicked(suggestion: SearchItemModel): Boolean
    }
//    private var searchItems  : MutableList<SearchItemModel> = mutableListOf()

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
        holder.apply {
            val context = holder.itemView.context
            if (isNoItemsFoundModeInitiated) {
                binding.searchItemContainer.isGone = true
                binding.noItemsTitle.text = context.getString(R.string.no_items_found)
                binding.noItemsFoundLayout.isVisible = true
            } else {
                val suggestion = searchItems[position]
                binding.searchTitle.text = suggestion.title
                binding.searchSubtitle.text = suggestion.body
                when (suggestion.itemType) {
                    SearchItemModel.ITEM_TYPE_SEARCH_SUGGESTION -> {
                        binding.searchSuggestionIcon.setImageResource(R.drawable.ic_search)
                    }
                    SearchItemModel.ITEM_TYPE_RECENT_SEARCH_SUGGESTION -> {
                        binding.searchSuggestionIcon.setImageResource(R.drawable.ic_history)
                    }
                }
                binding.root.setOnClickListener {
                    Snackbar.make(it, "Suggestion number $position", Snackbar.LENGTH_SHORT).show()
                    searchSuggestionListener.onSuggestionClicked(binding, suggestion)
                }
            }


        }
    }

    override fun getItemCount(): Int {

        return if (searchItems.isEmpty()) {
            //If searchItems list is empty, then set `isNoItemsFoundModeInitiated` to `true`
            //so that adapted will handle this case to notify user that `No match %QUERY%`
            isNoItemsFoundModeInitiated = true
            1
        } else searchItems.size
    }

    private var isNoItemsFoundModeInitiated: Boolean = false


    fun submitList(newSearchItems: MutableList<SearchItemModel>) {
        searchItems.clear()
        searchItems.addAll(newSearchItems)
        notifyDataSetChanged()
    }

}
