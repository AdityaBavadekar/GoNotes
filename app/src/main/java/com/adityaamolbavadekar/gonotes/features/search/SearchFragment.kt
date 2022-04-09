package com.adityaamolbavadekar.gonotes.features.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.ViewGroupCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaamolbavadekar.gonotes.GoNotes
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.FragmentSearchBinding
import com.adityaamolbavadekar.gonotes.databinding.ItemSearchBinding
import com.adityaamolbavadekar.gonotes.features.home.MainActivity
import com.adityaamolbavadekar.gonotes.features.note.datasource.NoteModel
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NoteViewModelFactory
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NotesViewModel
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.SearchAdapter
import com.adityaamolbavadekar.gonotes.features.search.source.SearchItemModel
import com.adityaamolbavadekar.gonotes.utils.toSearchItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialSharedAxis

class SearchFragment : Fragment(), SearchAdapter.SearchSuggestionListener {

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var searchAdapter : SearchAdapter
    private var mContext: FragmentActivity? = null
    private lateinit var binding: FragmentSearchBinding
    private var notesList : MutableList<NoteModel> = mutableListOf()
    private val notesViewModel: NotesViewModel by activityViewModels {
        NoteViewModelFactory((requireActivity().application as GoNotes).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = requireActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.material_motion_duration_long_1).toLong()
        }
        linearLayoutManager = LinearLayoutManager(mContext!!)
        searchAdapter = SearchAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        //Call to hide actionBar every time fragment starts
        (mContext!! as MainActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ViewGroupCompat.setTransitionGroup(binding.root,true)
        notesViewModel.allNotes.observe(viewLifecycleOwner,{
            notesList.clear()
            notesList.addAll(it)
        })
        binding.searchToolbar.setNavigationOnClickListener { findNavController(it).navigateUp() }
        binding.queryEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                val query = binding.queryEditText.text.toString()
                if (query.trim().isNotEmpty()){
                    Snackbar.make(binding.queryEditText,"Query : \"${query}\"",Snackbar.LENGTH_SHORT).apply {
                        animationMode = Snackbar.ANIMATION_MODE_SLIDE
                        show()
                    }
                    filterRecyclerView(query)
                }
                true
            }else false
        }
        setUpSuggestions()
    }

    private fun setUpSuggestions() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.searchRecyclerView.apply {
            adapter = searchAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun filterRecyclerView(query: String){
        val newFilteredItems = mutableListOf<SearchItemModel>()
        notesList.forEach { item->
            if(item.title.contains(query,true) || item.body.contains(query,true)){
                //Search query matches some text from note
                //convert Note into SearchItem
                newFilteredItems.add(item.toSearchItem())
            }
        }
        newFilteredItems.sortBy { it.title }
        searchAdapter.submitList(newFilteredItems)
    }

    override fun onSuggestionClicked(binding: ItemSearchBinding, suggestion: SearchItemModel) {}

    override fun onSuggestionLongClicked(suggestion: SearchItemModel): Boolean {
        //Show a dialog asking if user wants to delete the suggestion from search history if it
        // was not a search suggestion
        return false
    }

}