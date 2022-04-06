package com.adityaamolbavadekar.gonotes.features.home

import android.os.Bundle
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseActivity
import com.adityaamolbavadekar.gonotes.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        setupNavigation(true, binding.fragmentHolder.id)
    }

    override fun onDebug() {}
    override fun setTag(): String = "MainActivity"
    override fun setDescription(): String = "A Activity class that is Home."
}