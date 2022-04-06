package com.adityaamolbavadekar.gonotes.base

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.utils.*

abstract class BaseActivity : AppCompatActivity() {

    var mContext: Context? = null
    private lateinit var activityTAG: String
    private lateinit var fragmentDesc: String
    lateinit var pref: SharedPreferences
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    private var navigationUiEnabled : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        activityTAG = setTag()
        fragmentDesc = setDescription()
        mContext!!.onCreateCalled()
        if (BuildConfig.DEBUG) onDebug()
    }

    override fun onPause() {
        super.onPause()
        mContext!!.onPauseCalled()
    }

    override fun onStart() {
        super.onStart()
        mContext!!.onStartCalled()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyCalled(activityTAG)
    }

    override fun onResume() {
        super.onResume()
        mContext!!.onResumeCalled()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mContext!!.onConfigChangeOccurred(newConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navigationUiEnabled){
            navController.navigateUp() || super.onSupportNavigateUp()
        }else super.onSupportNavigateUp()
    }

    /**
     * Called only if installed app is in its [BuildConfig.DEBUG] build.
     */
    abstract fun onDebug()

    /**
     * Called to set Logging TAG.
     * */
    abstract fun setTag(): String

    /**
     * Called to set Logging Description.
     * */
    abstract fun setDescription(): String

    /**
     * Sets up the ActionBar returned by [AppCompatActivity.getSupportActionBar] for use
     * with a [NavHostFragment.getNavController].
     *
     * By calling this method, the title in the action bar will automatically be
     * updated when the destination changes (assuming there is a valid label).
     *
     * @see [NavigationUI.setupActionBarWithNavController]
     *
     * */
    fun setupNavigation(setup: Boolean = false,@IdRes fragmentContainer : Int? = null){
        if (!setup || fragmentContainer == null){
            navigationUiEnabled = false
            return
        }
        navHostFragment = supportFragmentManager.findFragmentById(fragmentContainer) as NavHostFragment
        navController  = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        navigationUiEnabled = true
    }

    /**
     * Configures app to change to fullScreen mode.
     * */
    fun toggleFullScreen() {

    }

}