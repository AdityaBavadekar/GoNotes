package com.adityaamolbavadekar.gonotes.features.note.modelconverters

import androidx.room.TypeConverter
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors

class GoNoteColoursTypeConverter {

    @TypeConverter
    fun toColour(colorName:String) : GoNotesColors{
        return GoNotesColors.valueOf(colorName)
    }

    @TypeConverter
    fun fromColour(color: GoNotesColors): String {
        return color.name
    }

}