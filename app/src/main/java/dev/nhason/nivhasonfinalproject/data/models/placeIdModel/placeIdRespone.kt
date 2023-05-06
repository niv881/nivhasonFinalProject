package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName

data class placeIdRespone(
    @SerializedName("html_attributions")
    val htmlAttributions: List<Any>,
    val result: Result,
    val status: String
)