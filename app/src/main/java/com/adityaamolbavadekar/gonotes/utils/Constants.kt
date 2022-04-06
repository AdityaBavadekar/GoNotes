package com.adityaamolbavadekar.gonotes.utils

import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel

const val createdGeneralFormType = "dd MMM H:mm"
const val NOTE = 0
const val HEADER = 1
const val EXTRA_END_ITEM = 2
const val TITLE_PINNED = "Pinned"
const val TITLE_STARRED = "Starred"
const val TITLE_LOCKED = "Locked"
const val booleanTrue = true

val noteItem = NoteModel(
    id = 0,
    title = "Title",
    body = "Body",
    label = "",
    created = 100,
    createdGeneralForm = createdGeneralFormType,
    edited = 100,
    editedGeneralForm = createdGeneralFormType,
    color = GoNotesColors.Blue.name,
    isPinned = false,
    isBinned = false/*Not yet implemented*/,
    isArchived = false/*Not yet implemented*/,
    isFavourite = false,
    isReminder = false,
    isLocked = false/*Not yet implemented*/,
    NOTE,
    ""
)
