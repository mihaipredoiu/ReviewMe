package com.example.reviewme.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reviewme.ReviewsAdapter
import com.example.reviewme.databinding.FragmentPlacesBinding
import com.example.reviewme.databinding.ReviewsListItemBinding.*
import com.bumptech.glide.Glide

class PlacesFragment : Fragment() {

    private lateinit var placesViewModel: PlacesViewModel
    private var _binding: FragmentPlacesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        placesViewModel =
            ViewModelProvider(this).get(PlacesViewModel::class.java)

        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        placesViewModel.locationId = arguments?.let { PlacesFragmentArgs.fromBundle(it).argId }!!

        val root: View = binding.root

        val itemTitle: TextView = binding.itemTitle
        val itemDescription: TextView = binding.itemDescription
        val itemPhoneNumber: TextView = binding.itemPhoneNumber

        val adapter = ReviewsAdapter()
        binding.reviewsList.adapter = adapter

        placesViewModel.photo.observe(viewLifecycleOwner) { photo ->         Glide
            .with(this)
            .load(photo)
            .into(binding.imageView);}

        placesViewModel.getLocationDetails()

        placesViewModel.reviews.observe(viewLifecycleOwner) { reviews -> adapter.data = reviews}

        placesViewModel.place.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                itemTitle.text = it.result.name
                itemDescription.text = it.result.formatted_address
                itemPhoneNumber.text = it.result.international_phone_number
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}