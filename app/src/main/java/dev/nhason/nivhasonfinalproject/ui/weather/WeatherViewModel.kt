package dev.nhason.nivhasonfinalproject.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.nhason.nivhasonfinalproject.BuildConfig
import dev.nhason.nivhasonfinalproject.data.models.weatherModel.WeatherResponse
import dev.nhason.nivhasonfinalproject.services.Services
import kotlinx.coroutines.launch

const val baseURL = BuildConfig.BASE_URL_OPEN_WEATHER
const val queryParam = BuildConfig.QUERY_PROPS_WEATHER
const val queryValue = BuildConfig.QUERY_KEY_OPEN_WEATHER

class WeatherViewModel(application : Application) : AndroidViewModel(application) {



    private val _locationLiveData = MutableLiveData<WeatherResponse>()
    val locationLiveData: LiveData<WeatherResponse> = _locationLiveData

        fun getWeather(lat : Double, lng: Double) {
            viewModelScope.launch {
                val result = Services.create(baseURL, queryParam, queryValue).getWeather(lat,lng)
                _locationLiveData.postValue(result)
            }

        }
    }


