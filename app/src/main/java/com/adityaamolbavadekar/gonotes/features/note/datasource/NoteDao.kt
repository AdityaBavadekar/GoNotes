package com.adityaamolbavadekar.gonotes.features.note.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY CREATED DESC")
    fun getNotes(): Flow<List<NoteModel>>

    @Query("SELECT * FROM notes_table ORDER BY CREATED DESC")
    fun getNote(ID : Int): Flow<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(NOTE: NoteModel)

    @Update
    suspend fun update(NOTE: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

}