package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE isBinned = :boolean ORDER BY created ASC")
    fun getNormalNotes(boolean: Boolean = false): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE id = :ID")
    fun getNoteWithId(ID: String): LiveData<NoteModel>

    @Query("SELECT * FROM notes_table WHERE createdGeneralForm = :CREATED_ON")
    fun getNotesCreatedOn(CREATED_ON: String): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE label IN(:LABEL)")
    fun getNotesForLabel(LABEL: String): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes_table WHERE isBinned = :boolean")
    fun getBinnedNotes(boolean: Boolean = true): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(NOTE: NoteModel)

    @Update
    suspend fun update(NOTE: NoteModel)

    @Query("DELETE FROM notes_table WHERE id =:ID")
    suspend fun deleteNoteWithId(ID: String)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

}