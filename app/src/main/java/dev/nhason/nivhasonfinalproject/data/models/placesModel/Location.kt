package dev.nhason.nivhasonfinalproject.data.models.placesModel



import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val lat: Double,
    val lng: Double
) : Parcelable