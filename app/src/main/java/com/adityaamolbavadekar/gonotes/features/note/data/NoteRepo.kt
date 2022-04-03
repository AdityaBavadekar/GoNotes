package com.adityaamolbavadekar.gonotes.features.note.data

import androidx.lifecycle.LiveData

interface NoteRepo {

    fun getNotes() : LiveData<List<NoteModel>>
    fun getNoteWithId(ID:String) : LiveData<NoteModel>
    fun getNotesCreatedOn(CREATED_ON:String) : LiveData<List<NoteModel>>
    suspend fun getNotesForLabel(LABEL:String) : LiveData<List<NoteModel>>
    suspend fun insert(NOTE: NoteModel)
    suspend fun update(NOTE: NoteModel)
    suspend fun deleteNoteWithId(ID:String)
    suspend fun deleteAll()

}