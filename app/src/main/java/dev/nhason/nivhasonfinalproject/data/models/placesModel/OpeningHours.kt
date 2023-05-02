package dev.nhason.nivhasonfinalproject.data.models.placesModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean
) : Parcelable