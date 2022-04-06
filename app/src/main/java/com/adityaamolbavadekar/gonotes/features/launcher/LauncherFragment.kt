package com.adityaamolbavadekar.gonotes.features.launcher

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentLauncherBinding
import kotlin.properties.Delegates

class LauncherFragment : BaseFragment() {

    private lateinit var binding: FragmentLauncherBinding
    private var startTime by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startTime = System.currentTimeMillis()
        mContext!!.actionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLauncherBinding.inflate(layoutInflater)
        viewModel.loadNotes()
        viewModel.getLoadingState().observe(viewLifecycleOwner, { isLoading ->
            if (!isLoading) {
                val controller = Navigation.findNavController(binding.root)
                controller.navigate(R.id.action_launcherFragment_to_viewNoteFragment)
            }
        })
        return binding.root
    }

    override fun onWelcomeNeeded() {

    }
    override fun onDebug() {}
    override fun setTag(): String = "LauncherFragment"
    override fun setDescription(): String =
        "A Fragment class which is show at start of app and it triggers view model data loading."
}