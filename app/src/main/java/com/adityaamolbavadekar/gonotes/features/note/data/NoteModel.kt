package com.adityaamolbavadekar.gonotes.features.note.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    val title: String,
    val body: String,
    val label: String,//TODO
    val created: Long,
    val createdGeneralForm: String,
    val edited: Long,
    val editedGeneralForm: String,
    val color: Int,
    val isPinned: Boolean,
    val isBinned: Boolean,
    val isArchived: Boolean,
    val isFavourite: Boolean,
    val isReminder: Boolean,
    val isLocked: Boolean
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {}

    companion object CREATOR : Parcelable.Creator<NoteModel> {
        override fun createFromParcel(parcel: Parcel): NoteModel {
            return NoteModel(parcel)
        }

        override fun newArray(size: Int): Array<NoteModel?> {
            return arrayOfNulls(size)
        }
    }
}

