package com.adityaamolbavadekar.gonotes.features.search.source

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchesDao {

    @Query("SELECT * FROM search_history_table ORDER BY NOTE_TITLE ASC")
    fun getSearchHistory(): Flow<List<SearchItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoSearchHistory(recentSearch: SearchItemModel)

    @Delete
    suspend fun deleteSearchItem(recentSearch: SearchItemModel)

    @Query("DELETE FROM search_history_table")
    suspend fun deleteAll()

}