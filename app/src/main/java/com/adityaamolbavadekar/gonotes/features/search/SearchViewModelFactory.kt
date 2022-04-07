package com.adityaamolbavadekar.gonotes.features.search

import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NotesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityaamolbavadekar.gonotes.features.search.source.RecentSearchesRepository

/**
 *
 * A [ViewModelProvider.Factory] which helps in getting instance of [SearchViewModel]
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class SearchViewModelFactory(private val repo: RecentSearchesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }


}