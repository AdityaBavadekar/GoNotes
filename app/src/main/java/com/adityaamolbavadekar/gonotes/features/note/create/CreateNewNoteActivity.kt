package com.adityaamolbavadekar.gonotes.features.note.create

import android.os.Bundle
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseActivity

class CreateNewNoteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation(true, R.id.fragmentHolder)
    }

    override fun onDebug() {}
    override fun setTag(): String = "CreateNewNoteActivity"
    override fun setDescription(): String = "A Activity class that is helps in creating a new note."
}