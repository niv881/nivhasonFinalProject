package dev.nhason.nivhasonfinalproject.data.models.placesModel


import com.google.gson.annotations.SerializedName


data class OpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean
)