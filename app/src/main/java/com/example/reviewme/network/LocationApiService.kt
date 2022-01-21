package com.example.reviewme.network


import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://maps.googleapis.com"

private val API_KEY =  System.getenv("REVIEW_ME_API_KEY") ?: "AIzaSyAZuwaDgYQkLe-uOBtJEbtMS_n3_Fd6SiM"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface LocationApiService {
    @GET("maps/api/place/textsearch/json")
    fun getLocations(@Query("query") query: String?, @Query("key") key: String = API_KEY): Call<String>

    @GET("maps/api/place/details/json")
    fun getLocationById(@Query("place_id") id: String, @Query("key") key: String = API_KEY): Call<String>
}

object LocationApi {
    val retrofitService : LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }
}
