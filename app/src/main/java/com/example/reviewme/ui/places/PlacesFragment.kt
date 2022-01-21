package com.example.reviewme.ui.places

import android.content.Context
import android.content.Intent
import android.net.Uri
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
        val itemRating: TextView = binding.itemRating
//        val itemStatus: TextView = binding.itemStatus
        val itemPhoneNumber: TextView = binding.itemPhoneNumber
        val itemWebsite: TextView = binding.itemWebsite

        val adapter = ReviewsAdapter()
        binding.reviewsList.adapter = adapter

        placesViewModel.photo.observe(viewLifecycleOwner) { photo ->         Glide
            .with(this)
            .load(photo)
            .into(binding.imageView);}

        placesViewModel.getLocationDetails()

        placesViewModel.reviews.observe(viewLifecycleOwner) { reviews -> adapter.data = reviews}

        placesViewModel.place.observe(viewLifecycleOwner, Observer { it ->
            if (it != null) {
                itemTitle.text = it.result.name
                itemDescription.text = it.result.formatted_address
                itemPhoneNumber.text = it.result.international_phone_number
                itemRating.text = it.result.rating.toString()
                itemWebsite.text = it.result.website

                val url = it.result.url

                binding.shareButton.setOnClickListener() {
                    val sendIntent: Intent = Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"))
                    sendIntent.putExtra("sms_body", "Hey! Look at this place: " + url);
                    startActivity(sendIntent);
                }

                val lat = it.result.geometry.location.lat
                val lng = it.result.geometry.location.lng

                binding.mapsButton.setOnClickListener() {
                    val mapIntent: Intent = Uri.parse(
                        "geo:$lat,$lng?z=14"
                    ).let {
                        // Or map point based on latitude/longitude
                        val location: Uri = Uri.parse("geo:$lat,$lng?z=19" ) // z param is zoom level
                        Intent(Intent.ACTION_VIEW, location)
                    }

                    startActivity(mapIntent)
                }

                val website = it.result.website

                binding.websiteButton.setOnClickListener() {
                    val webIntent: Intent = Uri.parse(website).let { webpage ->
                        Intent(Intent.ACTION_VIEW, webpage)
                    }

                    startActivity(webIntent)
                }

                val id = it.result.place_id

                binding.addButton.setOnClickListener() {
                    val filename = "savedPlaces"
                    var currentStorage: String = ""

                    context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->
                        lines.forEach { item -> currentStorage = item }
                    }

                    context?.openFileOutput(filename, Context.MODE_PRIVATE).use {
                        it?.write("$currentStorage $id".toByteArray())
                    }

                    context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->
                        lines.forEach { item -> System.out.println(item) }
                    }
                }

            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}