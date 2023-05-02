package dev.nhason.nivhasonfinalproject.data.models.placesModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import dev.nhason.nivhasonfinalproject.BuildConfig
import kotlinx.parcelize.Parcelize

const val ApiKey = BuildConfig.KEY_GOOGLE

@Parcelize
data class Photo(
    val height: Int,
    @SerializedName("html_attributions")
    val htmlAttributions: List<String>,
    @SerializedName("photo_reference")
    val photoReference: String?,
    val width: Int
) : Parcelable {
    val photoReferenceUrl get() = if(photoReference != null){
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=500&photo_reference=$photoReference&key=$ApiKey"
    }else{
        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f" +
                "/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"
    }
}