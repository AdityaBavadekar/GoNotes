package com.adityaamolbavadekar.gonotes.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel
import com.adityaamolbavadekar.gonotes.features.search.source.RecentSearchesRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: RecentSearchesRepository) : ViewModel() {

    val searchHistory: LiveData<List<SearchItemModel>> = repository.searchHistory.asLiveData()

    fun insertNote(recentSearch: SearchItemModel) =
        viewModelScope.launch { repository.insertIntoSearchHistory(recentSearch) }

    fun deleteNote(recentSearch: SearchItemModel) =
        viewModelScope.launch { repository.deleteSearchItem(recentSearch) }

}