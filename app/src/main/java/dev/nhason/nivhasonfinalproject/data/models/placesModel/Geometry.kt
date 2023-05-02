package dev.nhason.nivhasonfinalproject.data.models.placesModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geometry(
    val location: Location,
    val viewport: Viewport
) : Parcelable