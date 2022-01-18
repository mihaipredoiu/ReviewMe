package com.example.reviewme.classes

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.text.*


@Serializable
data class LocationWrapper(
    val next_page_token: String? = "",
    val results: MutableList<Location> = mutableListOf(),
    val status: String
)

@Serializable
data class Location(
    val name: String,
    val formatted_address: String,
    val opening_hours: OpeningHours? = null,
    val rating: Float? = 0.0f,
    val types: MutableList<String> = mutableListOf()
) {
    val status: String = if (opening_hours?.open_now == true) "Open" else "Close"
    override fun toString(): String = "$name $rating $formatted_address"
}

@Serializable
data class OpeningHours(
    val open_now: Boolean
)

fun main(args: Array<String>) {


    val format = Json { ignoreUnknownKeys = true }

    // parsing data back
//    val stringTest = """{
//        "business_status": "OPERATIONAL",
//        "formatted_address": "Bulevardul Regina Maria 43, București 040126, Romania",
//        "geometry": {},
//        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png",
//        "icon_background_color": "#FF9E67",
//        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/restaurant_pinlet",
//        "name": "Business Land Restaurant",
//        "opening_hours": {
//        "open_now": true
//        },
//        "photos": [],
//        "place_id": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
//        "plus_code": {},
//        "rating": 4.5,
//        "reference": "ChIJXXJSvhX-sUARa9HWY1H8cHA",
//        "types": [
//        "restaurant",
//        "food",
//        "point_of_interest",
//        "establishment"
//        ],
//        "user_ratings_total": 432
//    }"""

    val fullAnswerTest = """
        {
        "html_attributions": [],
        "next_page_token": "Aap_uEDa7nAR6IuOhMJA47OJRFB8aQ7AZvBgqIXu2NruBGw5b-QC_BzvWFY84kqaW5JSIiAu5Xt75fYB8Hfi4MTUnQde_lTcYkEcEvLhjhs7GylTdaPgEePtgX4Qe1-iCQ6eHGVssajo3oNA8K6vFd7-iWsOs3Rohdurl_4gtEoDkubClbM2TuyCO3tMsYk3C4i_xerrPk_v5RUCoeJLYcMKQSVXXHY7e3XlV6Xg9gpOEgk2hHRPKPdsikBPt7lP9h_K_aA8UL3uFuHOJp3kGrPADNUYd242xilseE1dPUMBggKUWIwIOZGG_C_a6ITKY_DpkEL7tuSERty6wkDFfIE_cIb9rPLmt7DYJDu3D_EkdOzT0Z-7QkdI1yV_-MilhzHg0TX9nJmBvNmJxD72iOa0B8IMuLUFdg",
        "results": [
        {
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
        },
        {
        "business_status": "OPERATIONAL",
        "formatted_address": "Bulevardul Eroii Sanitari 49A, București 050471, Romania",
        "geometry": {
        "location": {
        "lat": 44.4340341,
        "lng": 26.072512
        },
        "viewport": {
        "northeast": {
        "lat": 44.43533177989272,
        "lng": 26.07386317989273
        },
        "southwest": {
        "lat": 44.43263212010728,
        "lng": 26.07116352010728
        }
        }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png",
        "icon_background_color": "#FF9E67",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/restaurant_pinlet",
        "name": "Derby Pub & Restaurant",
        "opening_hours": {
        "open_now": true
        },
        "photos": [
        {
        "height": 1660,
        "html_attributions": [
        "<a href=\"https://maps.google.com/maps/contrib/105790085196909719715\">Derby</a>"
        ],
        "photo_reference": "Aap_uEAE4qRalnDk5B0N-wNrD5zu-YEG3DQ6L0oPrT967oAj2hny6g1zfMgfodsdZnMUvgJUOcpu6bGmJLnSoUDmVxIQq_Tdo-nQOOJ-okz7BXWorZi6VPLA9ybq6GtbPirElx_Ra5Duc7Iht8lNl8xuDKSL5EhzRX-9h54Hn3PWvv2UJ6Yg",
        "width": 2500
        }
        ],
        "place_id": "ChIJsYRVtGH_sUAR3qH-4wLg2tw",
        "plus_code": {
        "compound_code": "C3MF+J2 Bucharest",
        "global_code": "8GP8C3MF+J2"
        },
        "price_level": 2,
        "rating": 4.3,
        "reference": "ChIJsYRVtGH_sUAR3qH-4wLg2tw",
        "types": [
        "restaurant",
        "cafe",
        "bar",
        "food",
        "point_of_interest",
        "establishment"
        ],
        "user_ratings_total": 1604
        }
        ],
    "status": "OK"
    }
    """
    val obj =   format.decodeFromString<LocationWrapper>(fullAnswerTest)
   // println(obj) // MyModel(a=42, b="42")
    println(obj.status)
    println(obj.results[0].status)

}
