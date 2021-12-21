package com.bohurtadop.technicaltest.api

import com.bohurtadop.technicaltest.item.Team
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams") var teams: List<Team>
)