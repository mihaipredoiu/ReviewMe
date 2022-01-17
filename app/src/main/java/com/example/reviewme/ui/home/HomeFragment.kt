package com.example.reviewme.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.reviewme.LocationsSearchAdapter
import com.example.reviewme.R
import com.example.reviewme.databinding.FragmentHomeBinding

import android.view.*
import androidx.appcompat.widget.SearchView


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = LocationsSearchAdapter()
        binding.locationsList.adapter = adapter

        homeViewModel.locations.observe(viewLifecycleOwner) { locations -> adapter.data = locations}

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                homeViewModel.getResults(text)

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

