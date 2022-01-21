package com.example.reviewme.classes

import android.content.Context
import kotlinx.serialization.Serializable

@Serializable
data class DetailedLocationWrapper(
    val result: DetailedLocation = DetailedLocation(),
) {
    override fun toString(): String = "$result"
}

@Serializable
data class DetailedLocation(
    val name: String? = "",
    val formatted_address: String? = "",
    val international_phone_number: String? = "",
    val opening_hours: OpeningHours? = null,
    val rating: Float? = 0.0f,
    val url: String = "",
    val place_id: String = "",
    val types: MutableList<String>? = mutableListOf(),
    val photos: MutableList<Photo>? = mutableListOf(),
    val reviews: MutableList<Review>? = mutableListOf(),
    val website: String? = "",
    val geometry: Geometry = Geometry(location = GeometryLocation())
) {
    val status: String = if (opening_hours?.open_now == true) "Open Now" else "Closed"
    override fun toString(): String = "$name $rating $formatted_address"

    fun isSaved(context: Context?): Boolean {
        val filename = "savedPlaces"
        var currentStorage = ""

        context?.openFileInput(filename)?.bufferedReader()?.useLines { lines ->
            lines.forEach { item -> currentStorage = item }
        }

        val idTokens = currentStorage.trim().split("\\s+".toRegex())

        return place_id in idTokens
    }
}

@Serializable
data class Photo(
    val photo_reference: String? = ""
) {
    override fun toString(): String = "$photo_reference"
}

@Serializable
data class Review(
    val author_name: String? = "",
    val rating: Integer,
    val relative_time_description: String? = "",
    val text: String? = ""
) {
    override fun toString(): String = "$author_name ($rating, $relative_time_description): $text"
}

@Serializable
data class Geometry(
    val location: GeometryLocation
) {
    override fun toString(): String = "$location"
}

@Serializable
data class GeometryLocation(
    val lat: Double = 0.0,
    val lng: Double = 0.0
) {
    override fun toString(): String = "($lat, $lng)"
}
