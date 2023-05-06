package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName

data class CurrentOpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean,
    val periods: List<Period>,
    @SerializedName("weekday_text")
    val weekdayText: List<String>
)