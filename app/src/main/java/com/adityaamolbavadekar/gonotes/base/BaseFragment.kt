package com.adityaamolbavadekar.gonotes.base

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.viewbinding.BuildConfig
import com.adityaamolbavadekar.gonotes.GoNotes
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NoteViewModelFactory
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NotesViewModel
import kotlin.properties.Delegates

abstract class BaseFragment : Fragment() {

    /** [viewModels] gives you the ViewModel instance scoped to the current fragment.
     * This will be different for different fragments.
     *
     * [activityViewModels] gives you the ViewModel instance scoped to the current activity.
     * Therefore the instance will remain the same across multiple fragments in the same activity.
     * */
    val viewModel: NotesViewModel by activityViewModels {
        NoteViewModelFactory((requireActivity().application as GoNotes).repository)
    }
    var mContext: FragmentActivity? = null
    lateinit var fragmentTAG: String
    lateinit var fragmentDesc: String
    lateinit var pref: SharedPreferences
    private var welcomeNeeded by Delegates.notNull<Boolean>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = requireActivity()
        pref = PreferenceManager.getDefaultSharedPreferences(mContext!!)
        welcomeNeeded = pref.getBoolean("USER_WELCOMED",false)
        fragmentTAG = setTag()
        fragmentDesc = setDescription()
//        mContext!!.onAttachCalled(fragmentTAG, fragmentDesc)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(setHasMenu())
        if (BuildConfig.DEBUG) onDebug()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (welcomeNeeded) onWelcomeNeeded()
    }

    /**
     * Called only if installed app is in its [BuildConfig.DEBUG] build.
     */
    abstract fun onDebug()

    /**
     * Called inside [onViewCreated].
     *
     * Called only if the user has not seed this screen before or not tapped on
     * "Got it"/"Ok" button.
     *
     * Implement the InfoCardView or ShowcaseView here.
     */
    abstract fun onWelcomeNeeded()

    /**
     * Called inside [onPause].
     *
     * Sets value of [onWelcomeNeeded] to `false` from next run.
     *
     */
    fun setIsWelcomeNeeded(boolean: Boolean){
        pref.edit { putBoolean("WELCOME_NEEDED",boolean) }
        welcomeNeeded = boolean
    }

    /**
     * Called for [setHasOptionsMenu]
     *
     */
    abstract fun setHasMenu(): Boolean

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