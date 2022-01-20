package com.example.reviewme.ui.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.classes.DetailedLocationWrapper
import com.example.reviewme.network.LocationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.Response

class PlacesViewModel : ViewModel() {
    lateinit var arg_id : String

    var PLACE_ID = "ChIJTYUPei4AskARa8L4i012dis"
    var PLACE_URL = "https://maps.googleapis.com/maps/api/place/details/json?place_id=ChIJTYUPei4AskARa8L4i012dis&key=AIzaSyAZuwaDgYQkLe-uOBtJEbtMS_n3_Fd6SiM"

    private val _text = MutableLiveData<String>().apply {
        value = "This is places Fragment"
    }
    val text: LiveData<String> = _text
    private val place = MutableLiveData<DetailedLocationWrapper>().apply {
        value = null
    }

    fun getLocationDetails(id: String? = PLACE_ID) {
        LocationApi.retrofitService.getLocationById(PLACE_ID).enqueue(
            object: retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val format = Json { ignoreUnknownKeys = true }
                    val obj =  format.decodeFromString<DetailedLocationWrapper>(response.body().toString())

                    place.value = obj

                    System.out.println(obj)
                    System.out.println(arg_id)
                }

                override fun onFailure(call: Call<String>, t: Throwable) {}
            })
    }
}