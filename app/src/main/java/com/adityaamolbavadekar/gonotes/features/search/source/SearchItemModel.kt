package com.adityaamolbavadekar.gonotes.features.search.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Required for providing recent searches or search history
 *  in the search notes suggestions.
 * */
@Entity(tableName = "search_history_table")
data class SearchItemModel(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "NOTE_ID") var suggestedNoteReferenceId : Int,
    @ColumnInfo(name = "NOTE_TITLE") var title : String,
    @ColumnInfo(name = "NOTE_BODY") var body : String,
)