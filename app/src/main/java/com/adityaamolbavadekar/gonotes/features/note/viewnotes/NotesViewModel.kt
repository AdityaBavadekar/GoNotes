package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import androidx.lifecycle.*
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import com.adityaamolbavadekar.gonotes.usecases.create.NoteUtils
import com.hypertrack.hyperlog.HyperLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
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

    private val _noteWithId:  MutableLiveData<NoteModel> =
        MutableLiveData(NoteUtils.Creator().build())
    val noteWithId : LiveData<NoteModel> = _noteWithId
    
    private val _title : MutableLiveData<String>  = MutableLiveData("")
    private val _body : MutableLiveData<String>  = MutableLiveData("")
    val title : LiveData<String> = _title
    val body : LiveData<String> = _body
    fun updateTitle(text : String){
        _title.postValue(text)
    }
    fun updateBody(text : String){
        _body.postValue(text)
    }

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    fun insertNote(note: NoteModel) = viewModelScope.launch { repository.insert(note) }

    fun updateNote(note: NoteModel) = viewModelScope.launch {
        repository.update(note)
        _title.postValue(note.title)
        _body.postValue(note.body)
    }

    fun requestNote(ID: Int) = viewModelScope.launch {
        val noteModel = repository.getNote(ID).single()
        _noteWithId.postValue(noteModel)
    }

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
        if (BuildConfig.DEBUG) {
            viewModelScope.launch {
                for (i in 1..15) {
                    val tempNote = NoteUtils.Creator()
                        .withTitle("Note $i")
                        .withBody("Hello I am note number $i, I am supper loooooooooooooog. You can click me to edit me or view my full details")
                        .withFavourite(true)
                        .build()
                    insertNote(tempNote)
                }
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