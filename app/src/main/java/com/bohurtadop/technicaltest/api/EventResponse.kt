package com.bohurtadop.technicaltest.api

import com.bohurtadop.technicaltest.item.Event
import com.google.gson.annotations.SerializedName

data class EventResponse(@SerializedName("results") var events: List<Event>)
