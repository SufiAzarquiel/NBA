package net.azarquiel.nba.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
    var name: String,
    var conference: String,
    var record: String,
    @SerializedName("teamLogoUrl")
    var url: String
): Serializable
data class Player (
    var firstName: String,
    var lastName: String,
    var team: String,
    var position: String,
    var dateOfBirth: String,
    var height: String,
    var weight: String,
    var jerseyNumber: String,
    var age: String,
    var careerPoints: String,
    var careerBlocks: String,
    var careerAssists: String,
    var careerRebounds: String,
    var careerTurnovers: String,
    var careerPercentageThree: String,
    var careerPercentageFreethrow: String,
    var careerPercentageFieldGoal: String,
    var headShotUrl: String
)