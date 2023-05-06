package dev.nhason.nivhasonfinalproject.data.models.placeIdModel


import com.google.gson.annotations.SerializedName
import dev.nhason.nivhasonfinalproject.data.models.placesModel.ApiKey

data class Result(
    @SerializedName("address_components")
    val addressComponents: List<AddressComponent>,
    @SerializedName("adr_address")
    val adrAddress: String,
    @SerializedName("business_status")
    val businessStatus: String,
    @SerializedName("current_opening_hours")
    val currentOpeningHours: CurrentOpeningHours,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("formatted_phone_number")
    val formattedPhoneNumber: String,
    val geometry: Geometry,
    val icon: String,
    @SerializedName("icon_background_color")
    val iconBackgroundColor: String,
    @SerializedName("icon_mask_base_uri")
    val iconMaskBaseUri: String,
    @SerializedName("international_phone_number")
    val internationalPhoneNumber: String,
    val name: String,
    @SerializedName("opening_hours")
    val openingHours: OpeningHours,
    val photos: List<Photo>?,
    @SerializedName("place_id")
    val placeId: String,
    @SerializedName("plus_code")
    val plusCode: PlusCode,
    val rating: Double?,
    val reference: String,
    val reviews: List<Review>,
    val types: List<String>,
    val url: String,
    @SerializedName("user_ratings_total")
    val userRatingsTotal: Int,
    @SerializedName("utc_offset")
    val utcOffset: Int,
    val vicinity: String,
    val website: String,
    @SerializedName("wheelchair_accessible_entrance")
    val wheelchairAccessibleEntrance: Boolean
){
    val ratingDetails get() = rating?.toInt()?.toString() ?: "no rating"

    val photo get() = photos?.get(0)?.photoReferenceUrl
        ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f" +
                "/Placeholder_view_vector.svg/681px-Placeholder_view_vector.svg.png"

}