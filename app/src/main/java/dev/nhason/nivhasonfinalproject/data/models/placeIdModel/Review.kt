package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("author_name")
    val authorName: String,
    @SerializedName("author_url")
    val authorUrl: String,
    val language: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,
    val rating: Int,
    @SerializedName("relative_time_description")
    val relativeTimeDescription: String,
    val text: String,
    val time: Int,
    val translated: Boolean
)