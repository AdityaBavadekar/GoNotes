package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

/**
 *
 * A Repository for app which contains operations related to database.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
@Suppress("RedundantSuspendModifier")
class NoteRepository(private val dao: NoteDao) {

    val allNotes: Flow<List<NoteModel>> = dao.getNotes()
/*
    val binNotes: Flow<List<NoteModel>> = dao.getBinnedNotes()
    val normalNotes: Flow<List<NoteModel>> = dao.getNormalNotes()
    fun getNoteWithId(ID: Int): Flow<NoteModel> = dao.getNoteWithId(ID)
    fun getNotesCreatedOn(CREATED_ON: String): Flow<List<NoteModel>> = dao.getNotesCreatedOn(CREATED_ON)
    suspend fun getNotesForLabel(LABEL: String): Flow<List<NoteModel>> = dao.getNotesForLabel(LABEL)
*/

    @WorkerThread
    suspend fun insert(NOTE: NoteModel) {
        dao.insert(NOTE)
    }

    @WorkerThread
    suspend fun update(NOTE: NoteModel) {
        dao.update(NOTE)
    }
/*
    @WorkerThread
    suspend fun deleteNoteWithId(noteModel: NoteModel) {
        dao.deleteNote(noteModel)
    }

    */
    @WorkerThread
    suspend fun deleteAll() {
        dao.deleteAll()
    }

}