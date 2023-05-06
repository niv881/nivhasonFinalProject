package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName
import dev.nhason.nivhasonfinalproject.data.models.placesModel.ApiKey

data class Photo(
    val height: Int,
    @SerializedName("html_attributions")
    val htmlAttributions: List<String>,
    @SerializedName("photo_reference")
    val photoReference: String?,
    val width: Int
) {
    val photoReferenceUrl get() = if(photoReference != null){
        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=500&photo_reference=$photoReference&key=$ApiKey"
    }else{
        "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f" +
                "/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"
    }
}