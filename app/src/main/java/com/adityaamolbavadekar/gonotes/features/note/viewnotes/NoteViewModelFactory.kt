package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.datasource.*
import com.adityaamolbavadekar.gonotes.utils.NOTE
import com.adityaamolbavadekar.gonotes.utils.createdGeneralFormType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * A [ViewModel] which helps in getting list of notes from [NoteRepository] ,
 * which list is shown in [ViewNoteFragment] class.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022** *
 */
class ViewNotesViewModel : ViewModel() {

    private lateinit var repo: NoteRepository
    private val notes: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>().also {
            loadNotes()
        }
    }

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    fun getLoadingState(): LiveData<Boolean> {
        return isLoading
    }

    fun getNotesList(): LiveData<List<NoteModel>> {
        return notes
    }

    fun createSimpleNote(title:String,body:String,colors: GoNotesColors = GoNotesColors.Blue,isPinned : Boolean= false,isFavourite : Boolean = false){
        val time = System.currentTimeMillis()
        val timeFormat = SimpleDateFormat(
            createdGeneralFormType, Locale.ENGLISH
        ).format(Date()).toString()
        val noteItem = NoteModel(
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
        insertNote(note = noteItem)
    }

    private fun insertNote(note: NoteModel) {
        viewModelScope.launch { repo.insert(note) }
    }

    fun updateNote(note: NoteModel) {
        viewModelScope.launch { repo.update(note) }
    }

    private fun loadNotes() {
        repo.getNotes().observeForever {
            notes.postValue(it)
            postDelay()
        }
    }

    fun getRepo(context: Context) {
        val dao = NoteDatabase.getDatabaseInstance(context).dao
        repo = NoteRepository(dao)
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
                val tempNote =
                    NoteModel(
                    0,

                        "Note $i",
                        "Hello I am note number $i, I am supper loooooooooooooog. You can click me to edit me or view my full details",
                        "#Label$i",
                        time,
                        SimpleDateFormat(
                            createdGeneralFormType, Locale.ENGLISH
                        ).format(Date()).toString(),
                        time,
                        "Not yet edited"
                    ,

                        GoNotesColors.Blue.name,
                        isEven,
                        isBinned = false,
                        isArchived = false,
                        isFavourite = isEven,
                        false,
                        isEndingWithZero,
                        NOTE,
                        ""
                )
                insertNote(tempNote)
            }
        }
    }

    private fun postDelay(){
        viewModelScope.launch {
            delay(1500)
            isLoading.postValue(false)
        }
    }

}