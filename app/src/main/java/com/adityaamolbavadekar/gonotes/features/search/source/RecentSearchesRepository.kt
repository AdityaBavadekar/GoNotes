package com.adityaamolbavadekar.gonotes.features.search.source

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

class RecentSearchesRepository(private val dao: RecentSearchesDao) {

    val searchHistory: Flow<List<SearchItemModel>> = dao.getSearchHistory()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoSearchHistory(recentSearch: SearchItemModel) {
        dao.insertIntoSearchHistory(recentSearch)
    }

    suspend fun deleteSearchItem(recentSearch: SearchItemModel) {
        dao.deleteSearchItem(recentSearch)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

}