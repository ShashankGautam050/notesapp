package com.shashank.movieapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shashank.movieapp.R
import com.shashank.movieapp.databinding.FragmentHomeBinding
import com.shashank.movieapp.model.Notes
import com.shashank.movieapp.ui.adapters.NotesAdapter
import com.shashank.movieapp.viewmodel.NotesModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesModel by viewModels()
    private var myNotes = arrayListOf<Notes>()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getAllNotes().observe(viewLifecycleOwner) {
            myNotes = it as ArrayList<Notes>
            val staggerGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            binding.rvAllNotes.layoutManager = staggerGridLayoutManager
            notesAdapter =  NotesAdapter(context, it)
            binding.rvAllNotes.adapter = notesAdapter
        }

        binding.allNotes.setOnClickListener {
            viewModel.getAllNotes().observe(viewLifecycleOwner) {
                myNotes = it as ArrayList<Notes>
                val staggerGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggerGridLayoutManager
                notesAdapter =  NotesAdapter(context, it)
                binding.rvAllNotes.adapter = notesAdapter
            }
        }

        binding.filterLow.setOnClickListener {
            viewModel.lowNotes().observe(viewLifecycleOwner) {
                myNotes = it as ArrayList<Notes>
                val staggerGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggerGridLayoutManager
                notesAdapter =  NotesAdapter(context, it)
                binding.rvAllNotes.adapter = notesAdapter            }
        }

        binding.filterMedium.setOnClickListener {
            viewModel.mediumNotes().observe(viewLifecycleOwner) {
                myNotes = it as ArrayList<Notes>
                val staggerGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.rvAllNotes.layoutManager = staggerGridLayoutManager
                notesAdapter =  NotesAdapter(context, it)
                binding.rvAllNotes.adapter = notesAdapter            }
        }

        binding.filterHigh.setOnClickListener {
            viewModel.highNotes().observe(viewLifecycleOwner) {
                myNotes = it as ArrayList<Notes>
                binding.rvAllNotes.layoutManager = GridLayoutManager(context, 2)
                binding.rvAllNotes.adapter = NotesAdapter(context, it)
            }
        }

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as? SearchView
        if (searchView != null) {
            searchView.queryHint = "Search Notes ..."
        }
        searchView?.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFiltering(newText)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(newText: String?) {
       val newFilteredList = arrayListOf<Notes>()
        for (i in myNotes){
            if (i.title.contains(newText!!) || i.subTitle.contains(newText)){
                newFilteredList.add(i)
            }
        }

        notesAdapter.filterList(newFilteredList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
