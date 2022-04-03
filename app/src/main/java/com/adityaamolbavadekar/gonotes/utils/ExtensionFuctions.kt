package com.adityaamolbavadekar.gonotes.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.adityaamolbavadekar.gonotes.R
import com.google.android.material.snackbar.Snackbar

/**
 * A class which is helpful for other class for doing simple funtions
 * and to avoid repetitive code.
 *
 *
 */

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snack(message: String) {
    val snack = Snackbar.make(this,message,Snackbar.LENGTH_LONG)
    snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
}

