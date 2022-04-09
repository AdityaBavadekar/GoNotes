package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import com.adityaamolbavadekar.gonotes.features.search.source.RecentSearchesRepository

/**
 *
 * A [ViewModel] which helps in getting list of notes from [NoteRepository] ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NoteViewModelFactory(private val repo: NoteRepository,private val searchesRepository: RecentSearchesRepository? = null) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return if (searchesRepository != null){
                NotesViewModel(repo,searchesRepository) as T
            }else NotesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
    

}