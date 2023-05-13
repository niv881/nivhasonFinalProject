package dev.nhason.nivhasonfinalproject.data.models.placesModel

import com.google.gson.annotations.SerializedName


data class Places(
    @SerializedName("business_status")
    val businessStatus: String,
    val geometry: Geometry,
    val icon: String,
    @SerializedName("icon_background_color")
    val iconBackgroundColor: String,
    @SerializedName("icon_mask_base_uri")
    val iconMaskBaseUri: String,
    val name: String,
    @SerializedName("opening_hours")
    val openingHours: OpeningHours?,
    val photos: List<Photo>?,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("plus_code")
    val plusCode: PlusCode?,
    @SerializedName("price_level")
    val priceLevel: Int?,
    val rating: Double,
    val reference: String,
    val scope: String,
    val types: List<String>,
    @SerializedName("user_ratings_total")
    val userRatingsTotal: Int,
    val vicinity: String
){
    val photo
        get() = if (photos != null){
            photos[0].photoReferenceUrl
        }else{
            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f" +
                    "/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"
        }

}