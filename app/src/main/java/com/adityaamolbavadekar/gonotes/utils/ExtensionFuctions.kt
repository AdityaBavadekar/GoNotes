package com.adityaamolbavadekar.gonotes.utils

import android.content.Context
import android.content.res.Configuration
import android.view.View
import android.widget.Toast
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.google.android.material.snackbar.Snackbar
import com.hypertrack.hyperlog.HyperLog

/**
 * A class which is helpful for other class for doing simple functions
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
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
}

fun View.indefiniteSnack(message: String) {
    val snack = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
    snack.setAction("GO IT") {
        snack.dismiss()
    }
}

fun List<NoteModel>.filterBinnedNotes(): MutableList<NoteModel> {
    val list = mutableListOf<NoteModel>()
    forEach { noteModel ->
        if (!noteModel.isBinned) {
            list.add(noteModel)
        }
    }
    return list
}

fun List<NoteModel>.filterFor(query: String): MutableList<NoteModel> {
    val list = mutableListOf<NoteModel>()
    forEach { noteModel ->
        if (noteModel.itemType == NOTE) {
            noteModel.apply {
                if (title.contains(query) || body.contains(query) || label.contains(query)) {
                    list.add(noteModel)
                }
            }
        }
    }
    return list
}

fun Context.onCreateCalled() {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onCreate")
    }
}

fun Context.onPauseCalled() {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onPause")
    }
}

fun Context.onStartCalled() {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onStart")
    }
}

fun Context.onStopCalled() {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onStop")
    }
}

fun onDestroyCalled(fragmentTAG: String) {
    if (BuildConfig.DEBUG) {
        HyperLog.i(fragmentTAG, "onDestroy")
    }
}

fun Context.onResumeCalled() {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onResume")
    }
}

fun Context.onConfigChangeOccurred(newConfig: Configuration) {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onConfigurationChangeOccurred : $newConfig")
    }
}

fun Context.onAttachCalled(fragmentTAG: String, fragmentDesc: String) {
    if (BuildConfig.DEBUG) {
        HyperLog.i(this.javaClass.simpleName, "onAttach : Fragment[$fragmentTAG]($fragmentDesc)")
    }
}

fun onDetachCalled(fragmentTAG: String) {
    if (BuildConfig.DEBUG) {
        HyperLog.i(fragmentTAG, "onDetach : Fragment[$fragmentTAG]")
    }
}