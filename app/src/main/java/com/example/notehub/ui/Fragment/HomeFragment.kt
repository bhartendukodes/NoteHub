package com.example.notehub.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notehub.R
import com.example.notehub.databinding.FragmentHomeBinding
import com.example.notehub.model.Note
import com.example.notehub.ui.Adapter.NotesAdapter
import com.example.notehub.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    var notes:List<Note> = emptyList()

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }


    private val adapter : NotesAdapter by lazy {
        NotesAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel.initDb(applicatonContext = requireContext())
        setAdapter()
        onClick()
        filtering()
        observer()
        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
        binding.recView.layoutManager=staggeredGridLayoutManager


        return binding.root
    }

    private fun setAdapter() {
        binding.recView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recView.adapter = adapter
    }

    private fun observer() {
        homeViewModel.getNotes().observe(viewLifecycleOwner, { noteList ->
            notes=noteList
            adapter.submitList(noteList)
        })
    }

    private fun filtering() {
        binding.apply {
            ivHigh.setOnClickListener {
                homeViewModel.getHighNote().observe(viewLifecycleOwner, { noteList ->
                    notes=noteList
                    adapter.submitList(noteList)
                })
            }
            ivMedium.setOnClickListener {
                homeViewModel.getMediumNote().observe(viewLifecycleOwner, { noteList ->
                    notes=noteList
                    adapter.submitList(noteList)

                })
            }
            ivLow.setOnClickListener {
                homeViewModel.getLowNote().observe(viewLifecycleOwner, { noteList ->
                    notes=noteList
                    adapter.submitList(noteList)

                })
            }
            allNotes.setOnClickListener {
                homeViewModel.getNotes().observe(viewLifecycleOwner, { noteList ->
                    notes=noteList
                    adapter.submitList(noteList)

                })
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnAddNotes.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_createNotesFragments)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0.isNullOrEmpty()){
                    adapter.submitList(notes)
                }else{
                    filterNotes(p0)
                }
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun filterNotes(p0: String) {

        val newFilteredList = arrayListOf<Note>()

        Log.e("TAG","$p0 ")

        for (i in adapter.currentList){
            if (i.title.lowercase().contains(p0.lowercase()) || i.subTitle.lowercase().contains(p0.lowercase()))
            {
                newFilteredList.add(i)
            }
        }
        adapter.submitList(newFilteredList)
    }

}