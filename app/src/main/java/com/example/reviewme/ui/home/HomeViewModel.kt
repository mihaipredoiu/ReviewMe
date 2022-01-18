package com.example.reviewme.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reviewme.network.LocationApi
import retrofit2.Call
import retrofit2.Response
import java.net.URLEncoder
import javax.security.auth.callback.Callback
import com.example.reviewme.classes.Location
import com.example.reviewme.classes.LocationWrapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val locations: MutableLiveData<List<Location>> = MutableLiveData<List<Location>>().apply {
        val stringObj1 = """{
            "business_status": "OPERATIONAL",
            "formatted_address": "Bulevardul Regina Maria 43, București 040126, Romania",
            "geometry": {},
            "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png",
            "icon_background_color": "#FF9E67",
            "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/restaurant_pinlet",
            "name": "Business Land Restaurant",
            "opening_hours": {
            "open_now": true
            },
            "photos": [],
            "place_id": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
            "plus_code": {},
            "rating": 4.5,
            "reference": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
            "types": [
            "restaurant",
            "food",
            "point_of_interest",
            "establishment"
            ],
            "user_ratings_total": 432
        }"""

        val stringObj2 = """{
            "business_status": "OPERATIONAL",
            "formatted_address": "Bulevardul Regina Maria 43, București 040126, Romania",
            "geometry": {},
            "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png",
            "icon_background_color": "#FF9E67",
            "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/restaurant_pinlet",
            "name": "Claw's",
            "opening_hours": {
            "open_now": true
            },
            "photos": [],
            "place_id": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
            "plus_code": {},
            "rating": 4.5,
            "reference": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
            "types": [
            "restaurant",
            "food",
            "point_of_interest",
            "establishment"
            ],
            "user_ratings_total": 432
        }"""

        val format = Json { ignoreUnknownKeys = true }

        val testObj1 = format.decodeFromString<Location>(stringObj1)
        val testObj2 = format.decodeFromString<Location>(stringObj2)

        value = listOf(testObj1, testObj2)
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
                    //locations.value = listOf(response.body().toString().take(50), "C", "D", "S")
                    val format = Json { ignoreUnknownKeys = true }
                    val obj =  format.decodeFromString<LocationWrapper>(response.body().toString())
                    
                    locations.value = obj.results
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    //locations.value = listOf("E", "R", "R", "O", "R")
                }
            })
    }

}