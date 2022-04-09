package com.adityaamolbavadekar.gonotes.features.home

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseActivity
import com.adityaamolbavadekar.gonotes.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : BaseActivity(), NavController.OnDestinationChangedListener,
    TabLayout.OnTabSelectedListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentHolder) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener(this)
        addTabItems()
        binding.tabLayout.addOnTabSelectedListener(this)
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.viewNoteFragment -> {
                setupForViewNotesFragment()
            }
            R.id.editNoteFragment -> {
                setupForEditNoteFragment()
            }
            R.id.createNoteFragment -> {
                setupForCreateNoteFragment()
            }
            R.id.searchFragment -> {
                setupForSearchFragment()
            }
            R.id.feedbackFragment -> {
                setupForFeedbackFragment()
            }
        }
    }

    private fun setupForViewNotesFragment() {
        supportActionBar?.show()
    }

    private fun setupForEditNoteFragment() {
        binding.constraintLayout.isGone = true
        supportActionBar?.show()
    }

    private fun setupForCreateNoteFragment() {
        binding.constraintLayout.isGone = true
        supportActionBar?.show()
    }

    private fun setupForSearchFragment() {
        binding.constraintLayout.isGone = true
        supportActionBar?.hide()
    }

    private fun setupForFeedbackFragment() {
        binding.constraintLayout.isGone = true
        supportActionBar?.show()
    }

    private fun addTabItems() {
        val tabLayout = binding.tabLayout
        tabLayout.addTab(tabLayout.newTab().setId(1).setText("Notes"), 0, true)
        tabLayout.addTab(tabLayout.newTab().setId(2).setText("Favourites"), 1, false)
        tabLayout.addTab(tabLayout.newTab().setId(3).setText("Recycle Bin"), 2, false)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.id) {
            1 -> {
                changeCurrentDestinationTo(R.id.viewNoteFragment)
            }
            2 -> {
                changeCurrentDestinationTo(R.id.favouritesFragment)
            }
            3 -> {
                changeCurrentDestinationTo(R.id.recycleBinFragment)
            }
        }
    }

    private fun changeCurrentDestinationTo(@IdRes action: Int) {
        navController.navigate(action, null)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun setTag(): String = "MainActivity"
    override fun setDescription(): String = "A Activity class that is Home."

}