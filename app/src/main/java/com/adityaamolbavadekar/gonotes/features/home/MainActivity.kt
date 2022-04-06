package com.adityaamolbavadekar.gonotes.features.home

import android.os.Bundle
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation(true, R.id.fragmentHolder)
    }

    override fun onDebug() {}
    override fun setTag(): String = "MainActivity"
    override fun setDescription(): String = "A Activity class that is Home."
}