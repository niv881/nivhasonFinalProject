package dev.nhason.nivhasonfinalproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nhason.nivhasonfinalproject.BuildConfig
import dev.nhason.nivhasonfinalproject.data.models.placeIdModel.placeIdRespone
import dev.nhason.nivhasonfinalproject.data.models.placesModel.Places
import dev.nhason.nivhasonfinalproject.services.Services
import kotlinx.coroutines.launch

const val baseURL = BuildConfig.BASE_URL_GOOGLE
const val queryParam = BuildConfig.QUERY_PROPS_GOOGLE
const val queryValue =BuildConfig.QUERY_KEY_GOOGLE


class PlacesViewModel  : ViewModel() {

    private val _restaurantLiveData = MutableLiveData<List<Places>>()
    val restaurantLiveData: LiveData<List<Places>> = _restaurantLiveData

    private val _restaurantDetailsLiveData = MutableLiveData<placeIdRespone>()
    val restaurantDetailsLiveData: LiveData<placeIdRespone> = _restaurantDetailsLiveData

    private val _parksLiveData = MutableLiveData<List<Places>>()
    val parksLiveData: LiveData<List<Places>> = _parksLiveData

    private val _parkingLiveData = MutableLiveData<List<Places>>()
    val parkingLiveData: LiveData<List<Places>> = _parkingLiveData


    fun getRestaurant(latLng : String) {
        viewModelScope.launch {
            val resultR = Services.create(baseURL, queryParam, queryValue).getRestaurant(latLng)
            val resultParks = Services.create(baseURL, queryParam, queryValue).getParks(latLng)
            val resultParking = Services.create(baseURL, queryParam, queryValue).getParking(latLng)
            _restaurantLiveData.postValue(resultR.places)
            _parksLiveData.postValue(resultParks.places)
            _parkingLiveData.postValue(resultParking.places)
        }
    }

    fun getRestaurantDetails(place: Places){
        viewModelScope.launch {
            val details = Services.create(baseURL,queryParam, queryValue)
                .getRestaurantDetails(place.placeId)
            _restaurantDetailsLiveData.postValue(details)
        }
    }


}
