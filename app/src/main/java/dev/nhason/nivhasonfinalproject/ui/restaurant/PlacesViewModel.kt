package dev.nhason.nivhasonfinalproject.ui.restaurant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.nhason.nivhasonfinalproject.BuildConfig
import dev.nhason.nivhasonfinalproject.data.models.placesModel.Places
import dev.nhason.nivhasonfinalproject.services.Services
import kotlinx.coroutines.launch

const val baseURL = BuildConfig.BASE_URL_GOOGLE
const val queryParam = BuildConfig.QUERY_PROPS_GOOGLE
const val queryValue = BuildConfig.KEY_GOOGLE

class PlacesViewModel(application: Application)  : AndroidViewModel(application) {

    private val _locationLiveData = MutableLiveData<List<Places>>()
    val locationLiveData: LiveData<List<Places>> = _locationLiveData

    private val _parksLiveData = MutableLiveData<List<Places>>()
    val parksLiveData: LiveData<List<Places>> = _parksLiveData

    private val _parkingLiveData = MutableLiveData<List<Places>>()
    val parkingLiveData: LiveData<List<Places>> = _parkingLiveData


    fun getRestaurant(latLng : String) {
        viewModelScope.launch {

            val resultR = Services.create(baseURL, queryParam, queryValue).getRestaurant(latLng)
            val resultParks = Services.create(baseURL, queryParam, queryValue).getParks(latLng)
            val resultParking = Services.create(baseURL, queryParam, queryValue).getParking(latLng)
            _locationLiveData.postValue(resultR.places)
            _parksLiveData.postValue(resultParks.places)
            _parkingLiveData.postValue(resultParking.places)

        }

    }
}
