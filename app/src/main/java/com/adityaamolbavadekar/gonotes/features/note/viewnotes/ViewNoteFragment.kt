package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentViewNotesBinding
import com.adityaamolbavadekar.gonotes.databinding.InfoCardBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.logger.Logger.debugLog
import com.google.android.material.snackbar.Snackbar
import org.acra.log.debug

/**
 * A Fragment class which shows a list of all notes available in the database.
 * This class is also center for navigation to many other classes.
 * In case the app build type is `debug` then this class generates a random
 * list for testing purposes.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022**
 */
class ViewNoteFragment : BaseFragment() {

    private lateinit var binding: FragmentViewNotesBinding
    private lateinit var layoutManager: StaggeredGridLayoutManager
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    private var notesList: MutableList<NoteModel> = mutableListOf()
    private var searchItems: MutableList<NoteModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NoteAdapter(mContext!!, notesList)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        initNotesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initFab() {
        /*
        ViewGroupCompat.setTransitionGroup(binding.root,true)
        ViewCompat.setTransitionName(
            binding.addNoteButton,
            getString(R.string.transition_name_add_fab)
        )*/
        binding.addNoteButton.setOnClickListener {
            /*
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(
                    binding.addNoteButton,
                    getString(R.string.transition_name_create_note)
                )
                .build()
            val directions = ViewNoteFragmentDirections.actionViewNoteFragmentToCreateNoteFragment()
            Navigation.findNavController(it).navigate(directions, extras)*/

            Navigation.findNavController(it)
                .navigate(R.id.action_viewNoteFragment_to_createNoteFragment)
        }
    }

    private fun initTransition() {
        /* exitTransition = MaterialElevationScale(false).apply {
             duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
         }
         reenterTransition = MaterialElevationScale(true).apply {
             duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
         }*/
    }

    private fun initRecyclerView() {
        recyclerView = binding.notesRecyclerView
        val recyclerViewAnim = AnimationUtils.loadLayoutAnimation(
            recyclerView.context,
            R.anim.layout_recycler_view_item_fall_down
        )
        recyclerView.layoutAnimation = recyclerViewAnim
        recyclerView.scheduleLayoutAnimation()
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = this.layoutManager
    }

    private fun initNotesList() {
        viewModel.generateNotes()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*view.doOnPreDraw {
            startPostponedEnterTransition()
        }*/
        initTransition()
        initRecyclerView()
        initFab()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_view_note, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_search_view_notes)
        menuItem.setOnMenuItemClickListener { TODO() }
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
