package com.adityaamolbavadekar.gonotes.features.note.viewnotes

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnPreDraw
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.base.BaseFragment
import com.adityaamolbavadekar.gonotes.databinding.FragmentViewNotesBinding
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.utils.filterBinnedNotes
import com.adityaamolbavadekar.gonotes.utils.filterFor
import com.adityaamolbavadekar.gonotes.utils.indefiniteSnack
import com.google.android.material.transition.MaterialElevationScale

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
    private lateinit var searchLayoutManager: LinearLayoutManager
    private lateinit var adapter: NoteAdapter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private var notesList: MutableList<NoteModel> = mutableListOf()
    private var searchItems: MutableList<NoteModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NoteAdapter(mContext!!, notesList)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        searchAdapter = SearchAdapter(mContext!!, searchItems)
        searchLayoutManager = LinearLayoutManager(mContext!!)
        initNotesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewNotesBinding.inflate(layoutInflater)
        initTransition()
        initRecyclerView()
        initFab()
        return binding.root
    }


    private fun initFab() {
        ViewGroupCompat.setTransitionGroup(binding.root,true)
        ViewCompat.setTransitionName(
            binding.addNoteButton,
            getString(R.string.transition_name_add_fab)
        )
        binding.addNoteButton.setOnClickListener {
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(
                    binding.addNoteButton,
                    getString(R.string.transition_name_create_note)
                )
                .build()
            val directions = ViewNoteFragmentDirections.actionViewNoteFragmentToCreateNoteFragment()
            Navigation.findNavController(it).navigate(directions, extras)
        }
    }

    private fun initTransition(){
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_medium_2).toLong()
        }
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
        viewModel.allNotes.observe(viewLifecycleOwner){ notes->
            notes.let {
                notesList.addAll(notes.filterBinnedNotes())
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_view_note, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_search_view_notes)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnCloseListener {
            searchItems = mutableListOf()
            initRecyclerView()
            true
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                searchNotes(newText, searchView)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean = true
        })
    }

    /**
     * This `searchNotes(text: String?)` function searches
     * the database for notes which contain words similar to
     * to the user input.
     *
     * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
     * @since **April, 2022**
     */
    private fun searchNotes(text: String?, searchView: SearchView) { //TODO
        if (text == null) {
            searchItems = mutableListOf()
            initRecyclerView()
            return
        }
        searchItems = notesList.filterFor(text)
        if (searchItems.isEmpty()) {
            searchView.indefiniteSnack("Not notes found")
        } else {
            changeRecyclerViewConfig()
        }

    }

    private fun changeRecyclerViewConfig() {
        recyclerView.adapter = this.searchAdapter
        recyclerView.layoutManager = this.searchLayoutManager
    }


    override fun onDebug() {
        if (pref.getBoolean("GENERATE_LISTS", true)
        ) {
            viewModel.generateNotes()
        }
    }

    override fun onWelcomeNeeded() {

    }

    override fun setTag(): String = "ViewNoteFragment"
    override fun setDescription(): String =
        "A Fragment class which shows a list of all notes available in the database."
}
