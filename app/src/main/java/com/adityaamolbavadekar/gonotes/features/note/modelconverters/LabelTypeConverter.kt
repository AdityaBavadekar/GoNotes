package com.adityaamolbavadekar.gonotes.features.note.modelconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LabelTypeConverter {

    @TypeConverter
    fun toLabels(json : String) : ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(json,listType)
    }

    @TypeConverter
    fun fromLabels(list:ArrayList<String>):String{
        return Gson().toJson(list)
    }

}