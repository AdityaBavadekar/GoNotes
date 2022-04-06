package com.adityaamolbavadekar.gonotes.base

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.GoNotes
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NoteViewModelFactory
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NotesViewModel
import com.adityaamolbavadekar.gonotes.utils.*

abstract class BaseFragment : Fragment() {

    val viewModel: NotesViewModel by activityViewModels {
        NoteViewModelFactory((requireActivity().application as GoNotes).repository)
    }
    var mContext: FragmentActivity? = null
    lateinit var fragmentTAG: String
    lateinit var fragmentDesc: String
    lateinit var pref: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = requireActivity()
        pref = PreferenceManager.getDefaultSharedPreferences(mContext!!)
        fragmentTAG = setTag()
        fragmentDesc = setDescription()
        mContext!!.onAttachCalled(fragmentTAG, fragmentDesc)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext!!.onCreateCalled()
        setHasOptionsMenu(true)
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

    override fun onStop() {
        super.onStop()
        mContext!!.onStopCalled()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyCalled(fragmentTAG)
    }

    override fun onResume() {
        super.onResume()
        mContext!!.onResumeCalled()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mContext!!.onConfigChangeOccurred(newConfig)
    }

    override fun onDetach() {
        super.onDetach()
        onDetachCalled(fragmentTAG)
    }

    /**
     * Called only if installed app is in its [BuildConfig.DEBUG] build.
     */
    abstract fun onDebug()

    /**
     * Called only if the user has not seed this screen before or not tapped on
     * "Got it"/"Ok" button.
     *
     * Implement the [InfoCardView] here.
     */
    abstract fun onWelcomeNeeded()

    /**
     * Called to set Logging TAG.
     * */
    abstract fun setTag(): String

    /**
     * Called to set Logging Description.
     * */
    abstract fun setDescription(): String

    /**
     * Configures app to change to fullScreen mode.
     * */
    fun toggleFullScreen() {

    }

}