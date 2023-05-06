package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName

data class OpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean,
    val periods: List<PeriodX>,
    @SerializedName("weekday_text")
    val weekdayText: List<String>
)