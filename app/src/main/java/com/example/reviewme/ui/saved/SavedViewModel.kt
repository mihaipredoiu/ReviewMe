package com.example.reviewme.ui.notifications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.classes.DetailedLocation
import com.example.reviewme.classes.DetailedLocationWrapper
import com.example.reviewme.classes.Location
import com.example.reviewme.network.LocationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder

class NotificationsViewModel : ViewModel() {
    val locations: MutableLiveData<List<DetailedLocation>> = MutableLiveData<List<DetailedLocation>>().apply {
        value = listOf()
    }

    fun getSaved(ids: List<String>) {
        getSavedLocations(ids)
    }

    private fun getSavedLocations(ids: List<String>) {
        val tempLocation: MutableLiveData<List<DetailedLocation>> = MutableLiveData<List<DetailedLocation>>().apply {
            value = listOf()
        }

        ids.forEach { id ->
            LocationApi.retrofitService.getLocationById(URLEncoder.encode(id, "utf-8")).enqueue(
                object : retrofit2.Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        val format = Json { ignoreUnknownKeys = true }
                        val obj =
                            format.decodeFromString<DetailedLocationWrapper>(response.body().toString())

                        tempLocation.setValue(listOf(obj.result))
                        locations.value = locations.value.orEmpty() + tempLocation.value.orEmpty()
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {}
                })
        }
    }


    private val _navigateToLocationDetails = MutableLiveData<String?>()

    val navigateToLocationDetails: MutableLiveData<String?>
        get() = _navigateToLocationDetails

    fun doneNavigating() {
        _navigateToLocationDetails.value = null
    }

    fun onItemClicked(clickedItem: DetailedLocation) {
        _navigateToLocationDetails.value = clickedItem.place_id!!
        doneNavigating()
    }
}
