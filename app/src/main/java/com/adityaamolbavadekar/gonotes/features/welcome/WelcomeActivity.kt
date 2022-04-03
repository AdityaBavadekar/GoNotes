package com.adityaamolbavadekar.gonotes.features.welcome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adityaamolbavadekar.gonotes.databinding.FragmentWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}