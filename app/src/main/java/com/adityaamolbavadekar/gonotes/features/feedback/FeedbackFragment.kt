package com.adityaamolbavadekar.gonotes.features.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentSendFeedbackBinding
import com.google.android.material.snackbar.Snackbar

class FeedbackFragment : BaseFragment() {

    private lateinit var binding: FragmentSendFeedbackBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendFeedbackBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeScreenshotsTextView.setOnClickListener { snack(it,"View Screenshot") }
        binding.includeSystemLogsTextView.setOnClickListener { snack(it,"View System logs") }
    }

    fun snack(it:View,text:String){
        Snackbar.make(it, text, Snackbar.LENGTH_SHORT).apply {
            animationMode = Snackbar.ANIMATION_MODE_SLIDE
            show()
        }
    }

    override fun onWelcomeNeeded() {}
    override fun setHasMenu(): Boolean = false
    override fun setTag(): String = "FeedbackFragment"
    override fun setDescription(): String =
        "A Fragment class for sending feedback."

}