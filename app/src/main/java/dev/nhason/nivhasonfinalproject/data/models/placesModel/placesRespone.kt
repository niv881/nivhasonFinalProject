package dev.nhason.nivhasonfinalproject.data.models.placesModel


import com.google.gson.annotations.SerializedName

data class placesRespone(
    @SerializedName("html_attributions")
    val htmlAttributions: List<Any>,
    @SerializedName("results")
    val places: List<Places>,
    val status: String
)