package com.adityaamolbavadekar.gonotes.features.note.view

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.colors.toInt
import com.adityaamolbavadekar.gonotes.features.note.data.NoteDatabase
import com.adityaamolbavadekar.gonotes.features.note.data.NoteModel
import com.adityaamolbavadekar.gonotes.features.note.data.NoteRepoImpl
import com.adityaamolbavadekar.gonotes.utils.createdGeneralFormType
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * A [ViewModel] which helps in getting list of notes from [NoteRepoImpl] ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class ViewNotesViewModel : ViewModel() {

    private lateinit var repo: NoteRepoImpl
    private val notes: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>().also {
            loadNotes()
        }
    }

    fun getNotesList(): LiveData<List<NoteModel>> {
        return notes
    }

    fun insertNote(note: NoteModel) {
        viewModelScope.launch { repo.insert(note) }
    }

    fun updateNote(note: NoteModel) {
        viewModelScope.launch { repo.update(note) }
    }

    private fun loadNotes() {
        repo.getNotes().observeForever { notes.postValue(it) }
    }

    fun getRepo(context: Context) {
        val dao = NoteDatabase.getDatabaseInstance(context).dao
        repo = NoteRepoImpl(dao)
    }

    fun generateNotes() {
        viewModelScope.launch {
            for (i in 1..15) {
                val time = System.currentTimeMillis()
                val isEven =
                    "$i".endsWith("2") || "$i".endsWith("4") || "$i".endsWith("6") || "$i".endsWith(
                        "8"
                    ) || "$i".endsWith("0")
                val isEndingWithZero = "$i".endsWith("0")
                val tempNote = NoteModel(
                    0,
                    "Note $i",
                    "Hello I am note number $i, I am supper loooooooooooooog. You can click me to edit me or view my full details",
                    "#Label$i",
                    time,
                    SimpleDateFormat(
                        createdGeneralFormType, Locale.ENGLISH
                    ).format(Date()).toString(),
                    time,
                    "Not yet edited",
                    GoNotesColors.Blue.toInt(),
                    isEven,
                    isBinned = false,
                    isArchived = false,
                    isFavourite = isEven,
                    false,
                    isEndingWithZero
                )
                insertNote(tempNote)
            }
        }
    }


}