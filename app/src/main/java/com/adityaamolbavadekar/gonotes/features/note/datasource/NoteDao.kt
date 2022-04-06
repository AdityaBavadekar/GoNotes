package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY created DESC")
    fun getNotes(): Flow<List<NoteModel>>
/*
    @Query("SELECT * FROM notes_table WHERE isBinned = :boolean ORDER BY created ASC")
    fun getNormalNotes(boolean: Boolean = false): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE id = :ID")
    fun getNoteWithId(ID: Int): Flow<NoteModel>

    @Query("SELECT * FROM notes_table WHERE createdGeneralForm = :CREATED_ON")
    fun getNotesCreatedOn(CREATED_ON: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE label IN(:LABEL)")
    fun getNotesForLabel(LABEL: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE isBinned = :boolean")
    fun getBinnedNotes(boolean: Boolean = true): Flow<List<NoteModel>>
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(NOTE: NoteModel)

    @Update
    suspend fun update(NOTE: NoteModel)
/*
    @Delete
    suspend fun deleteNote(noteModel: NoteModel)
    */

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

}