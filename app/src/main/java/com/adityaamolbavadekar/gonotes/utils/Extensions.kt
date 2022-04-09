package com.adityaamolbavadekar.gonotes.utils

import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel

fun NoteModel.toSearchItem(): SearchItemModel {
    return SearchItemModel(id,id,title, body,SearchItemModel.ITEM_TYPE_SEARCH_SUGGESTION)
}