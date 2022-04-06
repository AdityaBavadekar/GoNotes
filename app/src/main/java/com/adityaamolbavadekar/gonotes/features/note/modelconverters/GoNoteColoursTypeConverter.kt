package com.adityaamolbavadekar.gonotes.features.note.typeconverters

import androidx.room.TypeConverter
import com.adityaamolbavadekar.gonotes.features.note.colors.GoNotesColors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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