package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.edit
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adityaamolbavadekar.gonotes.BuildConfig
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentViewNotesBinding
import com.adityaamolbavadekar.gonotes.databinding.ItemNoteBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.settings.SettingsActivity
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis

/**
 * A Fragment class which shows a list of all notes available in the database.
 * This class is also center for navigation to many other classes.
 * In case the app build type is `debug` then this class generates a random
 * list for testing purposes.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022**
 */
class ViewNoteFragment : BaseFragment(), NoteAdapter.NoteAdapterListener {

    private lateinit var binding: FragmentViewNotesBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var staggeredLayoutManager: StaggeredGridLayoutManager
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    private var isUsingLinearLayout: Boolean = false
    private var notesList: MutableList<NoteModel> = mutableListOf()
    private var searchItems: MutableList<NoteModel> = mutableListOf()

    private fun initOnClickTransition() {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        initTransition()
        initNotesList()
        initRecyclerView()
        initFab()
    }

    private fun initFab() {
        binding.addNoteButton.setOnClickListener { fab -> navigateToCreateNote(fab) }
    }

    private fun navigateToCreateNote(fab : View) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
        }
        Navigation.findNavController(fab)
            .navigate(R.id.action_viewNoteFragment_to_createNoteFragment)
    }

    private fun initTransition() {
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.fragmentHolder
            duration = resources.getInteger(R.integer.material_motion_duration_long_2).toLong()
            scrimColor = Color.TRANSPARENT
            try {
                setAllContainerColors(R.attr.colorSurface)
            } catch (e: Exception) {
                debugLog("$e [occurred while setting setAllContainerColors(R.attr.colorSurface)]")
            }
        }
    }

    private fun initRecyclerView() {
        adapter = NoteAdapter(mContext!!, notesList, this)
        staggeredLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        linearLayoutManager = LinearLayoutManager(mContext!!)
        recyclerView = binding.notesRecyclerView
        ViewGroupCompat.setTransitionGroup(recyclerView, true)
        val recyclerViewAnim = AnimationUtils.loadLayoutAnimation(
            recyclerView.context,
            R.anim.layout_recycler_view_item_fall_down
        )
        recyclerView.layoutAnimation = recyclerViewAnim
        recyclerView.scheduleLayoutAnimation()
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = this.staggeredLayoutManager
    }

    private fun initNotesList() {
        generateDebugList()

        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            if (!notes.isNullOrEmpty()) {
                val filtererNotes = mutableListOf<NoteModel>()
                notes.forEach {
                    if (!it.isBinned) {
                        filtererNotes.add(it)
                    }
                }
                notesList.addAll(filtererNotes)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun generateDebugList() {
        val generateNotes = pref.getBoolean("GENERATE_DEBUG_LISTS",false)
        val generatedNotesListPreviously = pref.getBoolean("GENERATED_LIST",false)
        if (BuildConfig.DEBUG && generateNotes && !generatedNotesListPreviously) {
            viewModel.generateNotes()
            pref.edit { putBoolean("GENERATED_LIST",true) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_view_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search_view_notes -> navigateToSearch()
            R.id.action_settings_view_notes -> {
                Intent(mContext!!, SettingsActivity::class.java).also { startActivity(it) }
                true
            }
            R.id.action_arrangement_view_notes ->  toggleLayoutManager(item)
            R.id.action_send_feedback_view_notes -> navigateToSendFeedback()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToSendFeedback(): Boolean {
        Navigation.findNavController(binding.root).navigate(R.id.action_viewNoteFragment_to_feedbackFragment)
        return true
    }

    private fun navigateToSearch(): Boolean {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_viewNoteFragment_to_searchFragment)
        return true
    }

    private fun toggleLayoutManager(item : MenuItem): Boolean {
        return if (isUsingLinearLayout) {
            isUsingLinearLayout = false
            item.title = "Use Linear layout"
            recyclerView.layoutManager = staggeredLayoutManager
            true
        } else {
            isUsingLinearLayout = true
            item.title = "Use Grid layout"
            recyclerView.layoutManager = linearLayoutManager
            true
        }
    }

    override fun onNoteClicked(cardView: View, note: NoteModel) {
        initOnClickTransition()
        val editNoteTransitionName = "edit_note_transition"
        val extras = FragmentNavigator.Extras.Builder()
            .addSharedElement(cardView, editNoteTransitionName)
            .build()
        val directions =
            ViewNoteFragmentDirections.actionViewNoteFragmentToEditNoteFragment(
                noteReferenceId = note.id
            )
        Navigation.findNavController(cardView).navigate(directions, extras)
    }

    override fun onNoteLongPressed(binding: ItemNoteBinding, note: NoteModel): Boolean {
        val snack = Snackbar.make(binding.root, "Note ${note.id}", Snackbar.LENGTH_SHORT)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.show()
        return true
    }

    override fun onWelcomeNeeded() {
        val infoCardView = mContext!!.findViewById<CardView>(R.id.mainInfoCardRootCardView)
        val infoTitleTextView = mContext!!.findViewById<TextView>(R.id.textViewTitle)
        val infoMessageTextView = mContext!!.findViewById<TextView>(R.id.textViewMessage)
        val infoPositiveButton = mContext!!.findViewById<Button>(R.id.buttonPositive)
        val infoNegativeButton = mContext!!.findViewById<Button>(R.id.buttonNegative)

        infoTitleTextView.text = "Welcome!"
        infoMessageTextView.text = "This is the place where you can find your notes and sort them."
        /*Dismiss i.e. hide when click event takes place*/
        val dismissBehaviorClickListener = View.OnClickListener { infoCardView.isVisible = false }

        infoPositiveButton.setOnClickListener(dismissBehaviorClickListener)
        infoNegativeButton.setOnClickListener(dismissBehaviorClickListener)
        infoTitleTextView.isVisible = true
        infoMessageTextView.isVisible = true
        infoPositiveButton.isVisible = true
        infoNegativeButton.isVisible = true
        infoCardView.isVisible = true
    }

    override fun setHasMenu(): Boolean = true
    override fun setTag(): String = "ViewNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which shows a list of all notes available in the database."

}
