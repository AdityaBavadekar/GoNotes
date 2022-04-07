package com.adityaamolbavadekar.gonotes.usecases.sort

import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class SortNotes {

    enum class OrderBy { ASCENDING, DESCENDING; }

    abstract class Sort(private val repo: NoteRepository, private val orderBy: OrderBy) {
        abstract fun sort(): Flow<List<NoteModel>>
    }


    class Date(private val repo: NoteRepository, private val orderBy: OrderBy, private val sortForCreatedDate: Boolean = false) : Sort(repo, orderBy) {
        override fun sort(): Flow<List<NoteModel>> {
            return repo.allNotes.map { notes ->
                when (orderBy) {
                    OrderBy.ASCENDING -> {
                        notes.sortedBy { if (sortForCreatedDate) it.created else it.edited }
                    }
                    OrderBy.DESCENDING -> {
                        notes.sortedByDescending { if (sortForCreatedDate) it.created else it.edited }
                    }
                }

            }
        }
    }

    class Time(private val repo: NoteRepository, private val orderBy: OrderBy, private val sortForCreatedDate: Boolean = false) : Sort(repo, orderBy) {
        override fun sort(): Flow<List<NoteModel>> {
            return repo.allNotes.map { notes ->
                when (orderBy) {
                    OrderBy.ASCENDING -> {
                        notes.sortedBy { SimpleDateFormat("H:mm:ss", Locale.ENGLISH).format(Date(if (sortForCreatedDate) it.created else it.edited)) }
                    }
                    OrderBy.DESCENDING -> {
                        notes.sortedByDescending { SimpleDateFormat("H:mm:ss", Locale.ENGLISH).format(Date(if (sortForCreatedDate) it.created else it.edited)) }
                    }
                }

            }
        }
    }

    /*TODO IMPLEMENT TO SORT LABELS A-Z*/
    class Labels(private val repo: NoteRepository, private val orderBy: OrderBy) : Sort(repo, orderBy) {
        override fun sort(): Flow<List<NoteModel>> {
            return repo.allNotes.map { notes ->
                when (orderBy) {
                    OrderBy.ASCENDING -> {
                        notes.sortedBy { it.created }
                    }
                    OrderBy.DESCENDING -> {
                        notes.sortedByDescending { it.created }
                    }
                }

            }
        }
    }

    class Title(private val repo: NoteRepository, private val orderBy: OrderBy) : Sort(repo, orderBy) {
        override fun sort(): Flow<List<NoteModel>> {
            return repo.allNotes.map { notes ->
                when (orderBy) {
                    OrderBy.ASCENDING -> {
                        notes.sortedBy { it.title }
                    }
                    OrderBy.DESCENDING -> {
                        notes.sortedByDescending { it.title }
                    }
                }

            }
        }
    }


}