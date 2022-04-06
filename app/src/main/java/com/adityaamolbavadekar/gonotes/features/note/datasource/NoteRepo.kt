package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface NoteRepo {

    fun getNotes() : Flow<List<NoteModel>>
    fun getNoteWithId(ID:String) : Flow<NoteModel>
    fun getNotesCreatedOn(CREATED_ON:String) : Flow<List<NoteModel>>
    suspend fun getNotesForLabel(LABEL:String) : Flow<List<NoteModel>>
    suspend fun insert(NOTE: NoteModel)
    suspend fun update(NOTE: NoteModel)
    suspend fun deleteNoteWithId(noteModel: NoteModel)
    suspend fun deleteAll()

}