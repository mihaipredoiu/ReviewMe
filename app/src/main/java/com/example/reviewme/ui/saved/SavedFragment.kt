package com.example.reviewme.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.reviewme.FavoriteListAdapter
import com.example.reviewme.LocationsSearchAdapter
import com.example.reviewme.R
import com.example.reviewme.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = FavoriteListAdapter{
                clickedItem -> notificationsViewModel.onItemClicked(clickedItem)
        }
        binding.locationsList.adapter = adapter

        notificationsViewModel.locations.observe(viewLifecycleOwner, Observer {
                locations -> adapter.data = locations
        })

        /** Get the ids of the places added to Favorites list */
        val filename = "savedPlaces"
        var idsString = ""
        context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->
            lines.forEach { item -> idsString = "$idsString $item" }
        }

        val idsList = idsString.trim().split("\\s+".toRegex())

        notificationsViewModel.getSaved(idsList)

        notificationsViewModel.navigateToLocationDetails.observe(viewLifecycleOwner, Observer { placeId ->
            placeId?.let {
                findNavController().navigate(
                    NotificationsFragmentDirections.actionNavigationNotificationsToNavigationPlaces(placeId))
            }})

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}