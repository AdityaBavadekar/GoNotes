package com.adityaamolbavadekar.gonotes.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog

abstract class BaseActivity : AppCompatActivity() {

    var mContext: Context? = null
    private var TAG: String = "BaseActivity"
    private lateinit var activityDesc: String
    lateinit var pref: SharedPreferences
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment
    private var navigationUiEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        TAG = setTag()
        activityDesc = setDescription()
        debugLog("onCreate called [description=${activityDesc}]")
    }

    override fun onStart() {
        super.onStart()
        debugLog("onStart Called")
    }
    override fun onRestart() {
        super.onRestart()
        debugLog("onRestart Called")
    }
    override fun onPause() {
        super.onPause()
        debugLog("onPause Called")
    }
    override fun onResume() {
        super.onResume()
        debugLog("onResume Called")
    }
    override fun onDestroy() {
        super.onDestroy()
        debugLog("onDestroy Called")
    }
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        debugLog("onDetachedFromWindow Called for context : ${window.context}")
    }
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        debugLog( "onDetachedFromWindow Called")
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navigationUiEnabled) {
            navController.navigateUp() || super.onSupportNavigateUp()
        } else super.onSupportNavigateUp()
    }

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
    fun setupNavigation(setup: Boolean = false, @IdRes fragmentContainer: Int? = null) {
        if (!setup || fragmentContainer == null) {
            navigationUiEnabled = false
            return
        }
        navHostFragment =
            supportFragmentManager.findFragmentById(fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        navigationUiEnabled = true
    }

    private var isFullScreen : Boolean = false

    /**
     * Configures app to change to fullScreen mode.
     * */
    fun toggleFullScreen() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        if (!isFullScreen) {
            isFullScreen = true
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        }else{
            isFullScreen = false
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }



}