package com.example.reviewme.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reviewme.classes.DetailedLocation
import com.example.reviewme.classes.LocationWrapper
import com.example.reviewme.databinding.FragmentDashboardBinding
import com.example.reviewme.network.LocationApi
import com.example.reviewme.ui.home.HomeViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder

class PlacesFragment : Fragment() {

    private lateinit var placesViewModel: PlacesViewModel
    private var _binding: FragmentDashboardBinding? = null

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

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        placesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        placesViewModel.getLocationDetails()

        placesViewModel.arg_id = arguments?.let { PlacesFragmentArgs.fromBundle(it).argId }!!


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}