package com.adityaamolbavadekar.gonotes.features.note.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val title: String,
    val body: String,
    val label: String,//TODO
    val created: Long,
    val createdGeneralForm: String,
    val edited: Long,
    val editedGeneralForm: String,
    val color: Int,
    val isPinned: Boolean,
    val isBinned: Boolean,
    val isArchived: Boolean,
    val isFavourite: Boolean,
    val isReminder: Boolean,
    val isLocked: Boolean,
)
