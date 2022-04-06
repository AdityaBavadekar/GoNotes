package com.adityaamolbavadekar.gonotes.features.note.datasource

import kotlinx.coroutines.flow.Flow

/**
 *
 * A Repository for app which contains operations related to database.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NoteRepository(private val dao: NoteDao) : NoteRepo {

    override val allNotes: Flow<List<NoteModel>> = dao.getNotes()

    override suspend fun insert(NOTE: NoteModel) = dao.insert(NOTE)

    override suspend fun update(NOTE: NoteModel) = dao.update(NOTE)

    override suspend fun deleteNote(NOTE: NoteModel) = dao.deleteNote(NOTE)

    override suspend fun deleteAll() = dao.deleteAll()
}