package com.example.reviewme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.network.LocationApi
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder
import javax.security.auth.callback.Callback

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val locations: MutableLiveData<List<String>> = MutableLiveData<List<String>>().apply {
        value = listOf("A", "B", "C", "D", "E")
    }

    fun getResults(query: String?) {
        if (query !== null) {
            System.out.println("I want to get results for $query")
            getHomeLocations(query)
        }
    }

    private fun getHomeLocations(query: String?) {
        LocationApi.retrofitService.getLocations(URLEncoder.encode(query, "utf-8")).enqueue(
            object: retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    locations.value = listOf(response.body().toString().take(50), "C", "D", "S")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    locations.value = listOf("E", "R", "R", "O", "R")
                }
            })
    }

}