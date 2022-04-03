package com.adityaamolbavadekar.gonotes.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.adityaamolbavadekar.gonotes.R

object NavigationUtils {

    fun toCreateNote(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_viewNoteFragment_to_createNoteFragment)
    }
    fun toEditNote(v: View,action: NavDirections) {
        Navigation.findNavController(v).navigate(action)
    }
    fun toBin(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_viewNoteFragment_to_binFragment)
    }

}