package dev.nhason.nivhasonfinalproject.data.models.weatherModel


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