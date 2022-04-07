package com.adityaamolbavadekar.gonotes.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseActivity
import com.adityaamolbavadekar.gonotes.databinding.ActivityMainBinding
import com.adityaamolbavadekar.gonotes.logger.Logger.infoLog
import org.acra.log.info

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding


    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragmentHolder)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        setupNavigation(false, binding.fragmentHolder.id, supportActionBar!!)
        binding.mainAddNoteButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.createNoteFragment,null)
        }
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentHolder) as NavHostFragment
        val controller = navHost.navController
        controller.addOnDestinationChangedListener { _, destination, _ ->
            infoLog("Fragment changed to ${destination.label}.")
        }

    }

    override fun setTag(): String = "MainActivity"
    override fun setDescription(): String = "A Activity class that is Home."
}