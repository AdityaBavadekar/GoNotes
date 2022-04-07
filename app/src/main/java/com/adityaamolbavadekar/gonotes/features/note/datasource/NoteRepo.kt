package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    val allNotes: Flow<List<NoteModel>>

    fun getNote(ID: Int): Flow<NoteModel>

    @WorkerThread
    suspend fun insert(NOTE: NoteModel)

    @WorkerThread
    suspend fun update(NOTE: NoteModel)

    @WorkerThread
    suspend fun deleteNote(NOTE: NoteModel)

    @WorkerThread
    suspend fun deleteAll()
}