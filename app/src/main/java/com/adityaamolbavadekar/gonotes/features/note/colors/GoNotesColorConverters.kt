package com.adityaamolbavadekar.gonotes.features.note.colors

import androidx.room.TypeConverter

/**
 *  **Returns GoNotesColor** Value of Int
 *  Eg :
 *  ```
 *  val i = GoNotesColors.Blue.toInt()
 *  val color : GoNotesColors = i.toInt()
 *  ```
 *  @param GoNotesColors.ordinal An integer
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022**
 */
@TypeConverter
fun Int.toGoNotesColors(): GoNotesColors {//goNotesColors: Int
    return GoNotesColors.values()[this]
}


/**
 *  **Returns Int** Value of GoNotesColor
 *  Eg :
 *  ```
 *  GoNotesColors.Blue.toInt()
 *  ```
 *  @param GoNotesColors A GoNotesColors type color
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022**
 */
@TypeConverter
fun GoNotesColors.toInt(): Int {//goNotesColors: GoNotesColors
    return this.ordinal
}