package com.adityaamolbavadekar.gonotes.features.note.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewbinding.BuildConfig
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.FragmentViewNotesBinding
import com.adityaamolbavadekar.gonotes.features.note.data.NoteModel
import com.adityaamolbavadekar.gonotes.utils.NavigationUtils

/**
 * A Fragment class which shows a list of all notes available in the database.
 * This class is also center for navigation to many other classes.
 * In case the app build type is `debug` then this class generates a random
 * list for testing purposes.
 *
 * @author [**Aditya Bavadekar**](https://github.com/AdityaBavadekar)
 * @since **April, 2022**
 */
class ViewNoteFragment : Fragment() {


    private lateinit var binding: FragmentViewNotesBinding
    private lateinit var layoutManager: StaggeredGridLayoutManager
    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: ViewNotesViewModel by activityViewModels()
    private var mContext: FragmentActivity? = null
    private var notesList: MutableList<NoteModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        mContext = requireActivity()
        viewModel.getRepo(mContext!!)
        if (BuildConfig.DEBUG && PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean("GENERATE_LISTS", true)
        ) {
            viewModel.generateNotes()
        }
        adapter = NoteAdapter(mContext!!, notesList)
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        initNotesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewNotesBinding.inflate(layoutInflater)
        initRecyclerView()
        initFab()
        return binding.root
    }

    private fun initFab() {
        binding.addNoteButton.setOnClickListener { NavigationUtils.toCreateNote(it) }
    }

    private fun initRecyclerView() {
        recyclerView = binding.notesRecyclerView
        recyclerView.adapter = this.adapter
        recyclerView.layoutManager = this.layoutManager
    }

    private fun initNotesList() {
        viewModel.getNotesList().observe(viewLifecycleOwner, { item ->
            item.forEach { note -> if (!notesList.contains(note)) notesList.add(note) }
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_view_note,menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.action_search_view_notes)
        val searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                searchNotes(newText)
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
    private fun searchNotes(text: String?) { //TODO
        if (text!=null){
        }else{
        }

    }

}