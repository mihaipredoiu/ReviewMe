package com.example.reviewme.classes

import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
//import kotlinx.serialization.json.Json
//import kotlin.text.*


@Serializable
data class Location(
    val name: String,
    val formatted_address: String,
    val open_now: Boolean,
    val rating: Float,
    val types: MutableList<String> = mutableListOf()
) {
}

//fun main(args: Array<String>) {
//
//
//    // parsing data back
//    val stringTest = """{
//         "formatted_address" : "Bulevardul Regina Maria 43, București 040126, Romania",
//         "name" : "Business Land Restaurant",
//	     "open_now" : true,
//         "rating" : 4.5,
//         "types" : [ "restaurant", "food", "point_of_interest", "establishment" ]
//      }"""
//    val obj =   Json.decodeFromString<Location>(stringTest)
//    println(obj) // MyModel(a=42, b="42")
//    println(obj.open_now)
//
//}
