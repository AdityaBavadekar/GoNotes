package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.lifecycle.LiveData

/**
 *
 * A Repository for app which contains operations related to database.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NoteRepoImpl(private val dao: NoteDao) : NoteRepo {

    override fun getNotes(): LiveData<List<NoteModel>> {
        return dao.getNotes()
    }

    override fun getNoteWithId(ID: String): LiveData<NoteModel> {
        return dao.getNoteWithId(ID)
    }

    override fun getNotesCreatedOn(CREATED_ON: String): LiveData<List<NoteModel>> {
        return dao.getNotesCreatedOn(CREATED_ON)
    }

    override suspend fun getNotesForLabel(LABEL: String): LiveData<List<NoteModel>> {
        return dao.getNotesForLabel(LABEL)
    }

    override suspend fun insert(NOTE: NoteModel) {
        dao.insert(NOTE)
    }

    override suspend fun update(NOTE: NoteModel) {
        dao.update(NOTE)
    }

    override suspend fun deleteNoteWithId(ID: String) {
        dao.deleteNoteWithId(ID)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}