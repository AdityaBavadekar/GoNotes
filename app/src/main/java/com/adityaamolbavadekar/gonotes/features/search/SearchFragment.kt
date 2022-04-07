package com.adityaamolbavadekar.gonotes.features.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityaamolbavadekar.gonotes.GoNotes
import com.adityaamolbavadekar.gonotes.R
import com.adityaamolbavadekar.gonotes.databinding.FragmentSearchBinding
import com.adityaamolbavadekar.gonotes.features.home.MainActivity
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.NotesViewModel
import com.adityaamolbavadekar.gonotes.features.note.viewnotes.SearchAdapter
import com.google.android.material.transition.MaterialSharedAxis

class SearchFragment : Fragment() {

    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var searchAdapter : SearchAdapter
    private var mContext: FragmentActivity? = null
    private lateinit var binding: FragmentSearchBinding

    val viewModel: NotesViewModel by activityViewModels {
        SearchViewModelFactory((requireActivity().application as GoNotes).searchHistoryRepository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = requireActivity()
        (mContext!! as MainActivity).supportActionBar?.hide()
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
        searchAdapter = SearchAdapter()
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
        binding.searchToolbar.setNavigationOnClickListener { findNavController(it).navigateUp() }
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


}