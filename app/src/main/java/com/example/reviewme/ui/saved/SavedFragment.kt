package com.example.reviewme.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reviewme.FavoriteListAdapter
import com.example.reviewme.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    private lateinit var savedViewModel: SavedViewModel
    private var _binding: FragmentSavedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedViewModel =
            ViewModelProvider(this).get(SavedViewModel::class.java)

        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = FavoriteListAdapter{
                clickedItem -> savedViewModel.onItemClicked(clickedItem)
        }
        binding.locationsList.adapter = adapter

        savedViewModel.locations.observe(viewLifecycleOwner, Observer {
                locations -> adapter.data = locations
        })

        /** Get the ids of the places added to Favorites list */
        val filename = "savedPlaces"
        var idsString = ""
        context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->
            lines.forEach { item -> idsString = "$idsString $item" }
        }

        val idsList = idsString.trim().split("\\s+".toRegex())

        savedViewModel.getSaved(idsList)

        savedViewModel.navigateToLocationDetails.observe(viewLifecycleOwner, Observer { placeId ->
            placeId?.let {
                findNavController().navigate(
                    SavedFragmentDirections.actionSavedToNavigationPlaces(placeId))
            }})

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}