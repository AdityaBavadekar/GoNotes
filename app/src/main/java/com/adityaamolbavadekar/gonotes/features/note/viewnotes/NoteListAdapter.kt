package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adityaamolbavadekar.gonotes.databinding.ItemNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel

class NoteListAdapter() : ListAdapter<NoteModel, NoteListAdapter.NoteListViewHolder>(DiffCallback) {


    class NoteListViewHolder(private var binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val viewHolder = NoteListViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.absoluteAdapterPosition
            onItemClicked(getItem(position))
        }

        return viewHolder
    }

    private fun onItemClicked(noteModel: NoteModel?) {

    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NoteModel>() {
            override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}