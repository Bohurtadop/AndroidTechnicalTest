package com.bohurtadop.technicaltest.item

import com.squareup.moshi.Json
import java.io.Serializable

class Event(
    val idEvent: String,
    val strEvent: String,
    val dateEvent: String,
    val strTime: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String,
    val intAwayScore: String
): Serializable {
}