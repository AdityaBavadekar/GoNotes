package com.adityaamolbavadekar.gonotes.features.note.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [NoteModel::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val dao : NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabaseInstance(context: Context): NoteDatabase {

            val subInstance = INSTANCE
            if (subInstance != null){
                return subInstance
            }else{
                synchronized(this){
                    val roomDatabaseINSTANCE = Room.databaseBuilder(
                        context,NoteDatabase::class.java,"notes_database"
                    ).build()
                    INSTANCE = roomDatabaseINSTANCE
                    return roomDatabaseINSTANCE
                }
            }
        }
    }

}