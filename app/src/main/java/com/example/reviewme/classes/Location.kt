package com.example.reviewme.classes

import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//import kotlin.text.*


@Serializable


data class Location(
    val name: String,
    val formatted_address: String,
    val opening_hours: OpeningHours,
    val rating: Float,
    val types: MutableList<String> = mutableListOf()
) {
    val status: String = if (opening_hours.open_now) "Open" else "Close"
    override fun toString(): String = name + rating.toString()
}

@Serializable
data class OpeningHours(
    val open_now: Boolean
)

//fun main(args: Array<String>) {
//
//
//    val format = Json { ignoreUnknownKeys = true }
//
//    // parsing data back
//    val stringTest = """{
//"business_status": "OPERATIONAL",
//"formatted_address": "Bulevardul Regina Maria 43, București 040126, Romania",
//"geometry": {},
//"icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png",
//"icon_background_color": "#FF9E67",
//"icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/restaurant_pinlet",
//"name": "Business Land Restaurant",
//"opening_hours": {
//"open_now": true
//},
//"photos": [],
//"place_id": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
//"plus_code": {},
//"rating": 4.5,
//"reference": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
//"types": [
//"restaurant",
//"food",
//"point_of_interest",
//"establishment"
//],
//"user_ratings_total": 432
//}"""
//    val obj =   format.decodeFromString<Location>(stringTest)
//    println(obj) // MyModel(a=42, b="42")
//    println(obj.status)
//    println(obj.opening_hours.open_now)
//
//}
