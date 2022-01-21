package com.example.reviewme.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.classes.Location
import com.example.reviewme.classes.LocationWrapper
import com.example.reviewme.network.LocationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder

class HomeViewModel : ViewModel() {
    val locations: MutableLiveData<List<Location>> = MutableLiveData<List<Location>>().apply {
        value = listOf()
    }

    fun getResults(query: String?) {
        if (query !== null) {
            getHomeLocations(query)
        }
    }

    private fun getHomeLocations(query: String?) {
        LocationApi.retrofitService.getLocations(URLEncoder.encode(query, "utf-8")).enqueue(
            object : retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val format = Json { ignoreUnknownKeys = true }
                    val obj = format.decodeFromString<LocationWrapper>(response.body().toString())

                    locations.value = obj.results
                }

                override fun onFailure(call: Call<String>, t: Throwable) {}
            })
    }

    private val _navigateToLocationDetails = MutableLiveData<String?>()

    val navigateToLocationDetails: MutableLiveData<String?>
        get() = _navigateToLocationDetails

    fun doneNavigating() {
        _navigateToLocationDetails.value = null
    }

    fun onItemClicked(clickedItem: Location) {
        _navigateToLocationDetails.value = clickedItem.place_id!!
        doneNavigating()
    }
}