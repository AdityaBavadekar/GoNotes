package com.adityaamolbavadekar.gonotes.features.note.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [NoteModel::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val dao: NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope,databaseName:String="notes_database"): NoteDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context, NoteDatabase::class.java, databaseName
                    )
                        .addCallback(NoteDatabaseCallback(scope))
                        .build()
                    context.debugLog("Database [name=${databaseName}] created")
                    INSTANCE = instance
                    instance
                }
        }
    }

    private class NoteDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dao)
                }
            }
        }

        suspend fun populateDatabase(noteDao: NoteDao) {
            noteDao.deleteAll()
            val note = NoteUtils.Creator()
                .withTitle("Welcome to Go Notes app!")
                .withBody("This note is for demonstration purpose")
                .build()
            noteDao.insert(note)
        }

    }

}