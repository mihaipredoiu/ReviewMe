package com.example.reviewme.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.classes.DetailedLocationWrapper
import com.example.reviewme.classes.Review
import com.example.reviewme.network.LocationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response

class PlacesViewModel : ViewModel() {
    lateinit var locationId : String

    var PLACE_ID = "ChIJTYUPei4AskARa8L4i012dis"
    var PLACE_URL = "https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJTYUPei4AskARa8L4i012dis&key=AIzaSyAZuwaDgYQkLe-uOBtJEbtMS_n3_Fd6SiM"
    var API_KEY = "AIzaSyAZuwaDgYQkLe-uOBtJEbtMS_n3_Fd6SiM"

    private val _place = MutableLiveData<DetailedLocationWrapper>().apply {
        value = null
    }
    val place: LiveData<DetailedLocationWrapper> = _place

    private val _reviews = MutableLiveData<List<Review>>().apply {
        value = listOf()
    }
    val reviews: LiveData<List<Review>> = _reviews

    private val _photo = MutableLiveData<String>().apply{
        value = ""
    }
    val photo: LiveData<String> = _photo

    fun getLocationDetails(id: String? = locationId) {
        LocationApi.retrofitService.getLocationById(locationId).enqueue(
            object: retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val format = Json { ignoreUnknownKeys = true }
                    val obj =  format.decodeFromString<DetailedLocationWrapper>(response.body().toString())

                    _place.value = obj
                    _reviews.value = obj.result.reviews
                    if (obj.result.photos?.size!! > 0) {
                        _photo.value = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=" + obj.result.photos[0].photo_reference + "&key=" + API_KEY
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {}
            })
    }
}