package com.adityaamolbavadekar.gonotes.usecases.create

import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel

class NoteUtils {

    class Creator {

        var _title: String = ""
        var _body: String = ""
        var _time: Long? = null
        var _color: String = GoNotesColors.Blue.name
        var _isPinned: Boolean = false
        var _isBinned: Boolean = false
        var _isFavourite: Boolean = false
        var _itemType: Int = ITEM_NOTE
        var _itemTitle: String = TITLE_NOTE

        fun withTitle(title: String): Creator {
            this._title = title
            return this
        }

        fun withBody(text: String): Creator {
            this._body = text
            return this
        }

        fun withTime(text: Long): Creator {
            this._time = text
            return this
        }

        fun withColor(text: GoNotesColors): Creator {
            this._color = text.name
            return this
        }

        fun withPinned(text: Boolean): Creator {
            this._isPinned = text
            return this
        }

        fun withBinned(text: Boolean): Creator {
            this._isBinned = text
            return this
        }

        fun withFavourite(text: Boolean): Creator {
            this._isFavourite = text
            return this
        }

        fun withItemType(text: Int): Creator {
            this._itemType = text
            return this
        }

        fun withItemTitle(text: String): Creator {
            this._itemTitle = text
            return this
        }


        fun build(): NoteModel {
            if (_time == null) {
                _time = System.currentTimeMillis()
            }
            return NoteModel(
                id = 0,
                title = _title,
                body = _body,
                created = _time!!,
                edited = _time!!,
                color = _color,
                isPinned = _isPinned,
                isBinned = _isBinned,
                isFavourite = _isFavourite,
                itemType = _itemType,
                itemTitle = _itemTitle
            )
        }

        companion object {
            const val ITEM_NOTE = 0
            const val ITEM_HEADER = 1
            const val ITEM_FOOTER = 2
            const val TITLE_NOTE = ""
            const val TITLE_BIN = "Notes in Bin"
            const val TITLE_PINNED = "Pinned"
            const val TITLE_FAVOURITE = "Favourite"
        }

    }

    class Updater {

        var _title: String? = null
        var _body: String? = null
        var _timeEdited: Long? = null
        var _color: String? = null
        var _isPinned: Boolean? = null
        var _isBinned: Boolean? = null
        var _isFavourite: Boolean? = null
        var _itemType: Int? = null
        var _itemTitle: String? = null

        fun withTitle(title: String): Updater {
            this._title = title
            return this
        }

        fun withBody(text: String): Updater {
            this._body = text
            return this
        }

        fun withEditedTime(text: Long): Updater {
            this._timeEdited = text
            return this
        }

        fun withColor(text: GoNotesColors): Updater {
            this._color = text.name
            return this
        }

        fun withPinned(text: Boolean): Updater {
            this._isPinned = text
            return this
        }

        fun withBinned(text: Boolean): Updater {
            this._isBinned = text
            return this
        }

        fun withFavourite(text: Boolean): Updater {
            this._isFavourite = text
            return this
        }

        fun withItemType(text: Int): Updater {
            this._itemType = text
            return this
        }

        fun withItemTitle(text: String): Updater {
            this._itemTitle = text
            return this
        }

        fun build(note: NoteModel): NoteModel {
            if (_timeEdited == null) {
                _timeEdited = System.currentTimeMillis()
            }

            return NoteModel(
                id = 0,
                title = _title ?: note.title,
                body = _body ?: note.body,
                created = note.created, /*Creation of note will always be a constant*/
                edited = _timeEdited!!, /*Edited time of note will always change*/
                color = _color ?: note.color,
                isPinned = _isPinned ?: note.isPinned,
                isBinned = _isBinned ?: note.isBinned,
                isFavourite = _isFavourite ?: note.isFavourite,
                itemType = _itemType ?: note.itemType,
                itemTitle = _itemTitle ?: note.itemTitle
            )
        }

        companion object {
            const val ITEM_NOTE = 0
            const val ITEM_HEADER = 1
            const val ITEM_FOOTER = 2
            const val TITLE_NOTE = ""
            const val TITLE_BIN = "Notes in Bin"
            const val TITLE_PINNED = "Pinned"
            const val TITLE_FAVOURITE = "Favourite"
        }

    }
}