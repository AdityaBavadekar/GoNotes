
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
    val label: String,
    val created: Long,
    val createdGeneralForm: String,
    val edited: Long,
    val editedGeneralForm: String,
    val color: String,
    val isPinned: Boolean,
    val isBinned: Boolean,
    val isArchived: Boolean,
    val isFavourite: Boolean,
    val isReminder: Boolean,
    val isLocked: Boolean,
    var itemType: Int = NOTE,
    var itemTitle: String = ""
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
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeString(label)
        parcel.writeLong(created)
        parcel.writeString(createdGeneralForm)
        parcel.writeLong(edited)
        parcel.writeString(editedGeneralForm)
        parcel.writeString(color)
        parcel.writeByte(if (isPinned) 1 else 0)
        parcel.writeByte(if (isBinned) 1 else 0)
        parcel.writeByte(if (isArchived) 1 else 0)
        parcel.writeByte(if (isFavourite) 1 else 0)
        parcel.writeByte(if (isReminder) 1 else 0)
        parcel.writeByte(if (isLocked) 1 else 0)
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



