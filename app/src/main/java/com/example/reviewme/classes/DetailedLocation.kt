package com.example.reviewme.classes

import kotlinx.serialization.Serializable

@Serializable
data class DetailedLocationWrapper(
    val result: DetailedLocation,
){
    override fun toString(): String = "$result"
}

@Serializable
data class DetailedLocation(
    val name: String? = "",
    val formatted_address: String? = "",
    val international_phone_number: String? = "",
    val opening_hours: OpeningHours? = null,
    val rating: Float? = 0.0f,
    val types: MutableList<String>? = mutableListOf(),
    val photos: MutableList<Photo>? = mutableListOf(),
    val reviews: MutableList<Review>? = mutableListOf(),
    val website: String? = "",
    val geometry: Geometry
) {
    override fun toString(): String = "$name $rating $formatted_address"
}

@Serializable
data class Photo(
    val photo_reference: String? = ""
){
    override fun toString(): String = "$photo_reference"
}

@Serializable
data class Review(
    val author_name: String? = "",
    val rating: Integer,
    val relative_time_description: String? =  "",
    val text: String? = ""
){
    override fun toString(): String = "$author_name ($rating, $relative_time_description): $text"
}

@Serializable
data class Geometry(
    val location: GeometryLocation
){
    override fun toString(): String = "$location"
}

@Serializable
data class GeometryLocation(
    val lat: Double,
    val lng: Double
){
    override fun toString(): String = "($lat, $lng)"
}
