package dev.nhason.nivhasonfinalproject.data.models.weatherModel


import com.google.gson.annotations.SerializedName
import dev.nhason.nivhasonfinalproject.data.models.placesModel.ApiKey

data class Weather(
    val description: String,
    val icon: String?,
    val id: Int,
    val main: String
){
    val iconUrl get() = if(icon != null){
        "https://openweathermap.org/img/wn/$icon@2x.png"
    }else{
            "https://www.clipartmax.com/png/middle/249-2493751_no-rain-icon-weather-seasons-icons-in-svg-and-png-iconscout-no.png"
    }
}