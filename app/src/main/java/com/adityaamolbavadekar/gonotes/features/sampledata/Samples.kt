package com.adityaamolbavadekar.gonotes.features.sampledata

import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel
import java.util.*

object Samples {


    val searchItems: MutableList<SearchItemModel> = createSearchItems(35)

    private fun createSearchItems(count: Int): MutableList<SearchItemModel> {
        val items: MutableList<SearchItemModel> = mutableListOf()
        for (i in 0..count) {
            val suggestion = SearchItemModel(
                i,
                i + 1,
                "Title $i",
                "$i ${Date()}",
                SearchItemModel.ITEM_TYPE_SEARCH_SUGGESTION
            )
            items.add(suggestion)
        }
        return items
    }

}
