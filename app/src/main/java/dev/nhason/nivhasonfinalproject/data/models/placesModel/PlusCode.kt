package dev.nhason.nivhasonfinalproject.data.models.placesModel


import com.google.gson.annotations.SerializedName


data class PlusCode(
    @SerializedName("compound_code")
    val compoundCode: String,
    @SerializedName("global_code")
    val globalCode: String
)