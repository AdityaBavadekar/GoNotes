package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import androidx.preference.PreferenceManager
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import com.adityaamolbavadekar.gonotes.usecases.Themes
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils
import com.hypertrack.hyperlog.HyperLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * A [ViewModel] which helps in getting list of notes from [NoteRepository] ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class NotesViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<NoteModel>> = repository.allNotes.asLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun insertNote(note: NoteModel) = viewModelScope.launch { repository.insert(note) }

    fun updateNote(note: NoteModel) = viewModelScope.launch { repository.update(note) }

    fun loadNotes() {
        val start = System.currentTimeMillis()
        HyperLog.i("ViewModel", "Loading Notes...")
        when (val list = allNotes.value) {
            null -> {
                HyperLog.d("ViewModel", "Notes not found posting delay.")
                postDelay()
            }
            else -> {
                HyperLog.d(
                    "ViewModel",
                    "${list.size} Notes found in ${System.currentTimeMillis() - start}ms."
                )
                HyperLog.d("ViewModel", "Counting notes inside bin...")
                val bin = mutableListOf<NoteModel>()
                list.forEach {
                    if (it.isBinned) bin.add(it)
                }
                HyperLog.d(
                    "ViewModel",
                    if (bin.isNotEmpty()) "${bin.size} notes found inside bin." else "No notes found inside bin."
                )
                HyperLog.d("ViewModel", "Posting delay.")
                postDelay()
            }
        }
    }

    fun generateNotes() {
        viewModelScope.launch {
            for (i in 1..15) {
                val time = System.currentTimeMillis()
                val tempNote = NoteUtils.Creator()
                    .withTitle("Note $i")
                    .withBody("Hello I am note number $i, I am supper loooooooooooooog. You can click me to edit me or view my full details",)
                    .withFavourite(true)
                    .build()
                insertNote(tempNote)
            }
        }
    }

    private fun postDelay() {
        viewModelScope.launch {
            delay(1500)
            _isLoading.postValue(false)
        }
    }

}