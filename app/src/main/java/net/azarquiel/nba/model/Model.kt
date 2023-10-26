package net.azarquiel.nba.model

import com.google.gson.annotations.SerializedName

data class Team(
    var name: String,
    var conference: String,
    var record: String,
    @SerializedName("teamLogoUrl")
    var url: String
)