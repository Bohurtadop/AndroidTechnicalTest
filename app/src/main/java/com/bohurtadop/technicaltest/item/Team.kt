package com.bohurtadop.technicaltest.item

import java.io.Serializable

open class Team(
    val idTeam: String,
    val strTeam: String,
    val strDescriptionEN: String,
    val intFormedYear: String,
    val strStadium: String,
    val strTeamBadge: String,
    val strTeamJersey: String,
    val strWebsite: String,
    val strFacebook: String,
    val strTwitter: String,
    val strInstagram: String,
    val strYoutube: String
) : Serializable {
}