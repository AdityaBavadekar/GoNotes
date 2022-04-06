package com.adityaamolbavadekar.gonotes.features.note.datasource

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "TITLE") var title: String,
    @ColumnInfo(name = "BODY") var body: String,
    @ColumnInfo(name = "CREATED") val created: Long,
    @ColumnInfo(name = "EDITED") var edited: Long,
    @ColumnInfo(name = "COLOR") var color: String,
    @ColumnInfo(name = "IS_PINNED") var isPinned: Boolean,
    @ColumnInfo(name = "IS_BINNED") var isBinned: Boolean,
    @ColumnInfo(name = "IS_FAVOURITE") var isFavourite: Boolean,
    @ColumnInfo(name = "ITEM_TYPE") var itemType: Int,
    @ColumnInfo(name = "ITEM_TITLE") var itemTitle: String

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString()!!,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeLong(created)
        parcel.writeLong(edited)
        parcel.writeString(color)
        parcel.writeByte(if (isPinned) 1 else 0)
        parcel.writeByte(if (isBinned) 1 else 0)
        parcel.writeByte(if (isFavourite) 1 else 0)
        parcel.writeInt(itemType)
        parcel.writeString(itemTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteModel> {
        override fun createFromParcel(parcel: Parcel): NoteModel {
            return NoteModel(parcel)
        }

        override fun newArray(size: Int): Array<NoteModel?> {
            return arrayOfNulls(size)
        }
    }

}



