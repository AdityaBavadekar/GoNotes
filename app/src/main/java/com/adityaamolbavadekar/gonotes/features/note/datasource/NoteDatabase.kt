package com.adityaamolbavadekar.gonotes.features.note.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.utils.NOTE
import com.adityaamolbavadekar.gonotes.utils.createdGeneralFormType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context, NoteDatabase::class.java, "notes_database"
                    )
                        .addCallback(NoteDatabaseCallback(scope))
                        .build()
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
            val note = createSimpleNote(
                "Welcome, this note is for demonstration purpose.",
                "Write you description here..."
            )
            noteDao.insert(note)
        }

        fun createSimpleNote(
            title: String,
            body: String,
            colors: GoNotesColors = GoNotesColors.Blue,
            isPinned: Boolean = false,
            isFavourite: Boolean = false
        ): NoteModel {
            val time = System.currentTimeMillis()
            val timeFormat = SimpleDateFormat(
                createdGeneralFormType, Locale.ENGLISH
            ).format(Date()).toString()
            return NoteModel(
                id = 0,
                title = title,
                body = body,
                label = "",
                created = time,
                createdGeneralForm = timeFormat,
                edited = time,
                editedGeneralForm = timeFormat,
                color = colors.name,
                isPinned = isPinned,
                isBinned = false/*Not yet implemented*/,
                isArchived = false/*Not yet implemented*/,
                isFavourite = isFavourite,
                isReminder = false,
                isLocked = false/*Not yet implemented*/,
                NOTE,
                ""
            )
        }


    }

}