package com.adityaamolbavadekar.gonotes.features.search.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog

@Database(
    entities = [SearchItemModel::class],
    version = 1,
    exportSchema = false
)
abstract class RecentSearchesDatabase : RoomDatabase() {

    abstract val dao: RecentSearchesDao

    companion object {
        @Volatile
        private var INSTANCE: RecentSearchesDatabase? = null
        fun getDatabase(
            context: Context,
            databaseName: String = "search_history_database"
        ): RecentSearchesDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context, RecentSearchesDatabase::class.java, databaseName
                    )
                        .build()
                    context.debugLog("Database [name=${databaseName}] created")
                    INSTANCE = instance
                    instance
                }
        }
    }

}