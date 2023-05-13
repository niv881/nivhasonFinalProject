package dev.nhason.nivhasonfinalproject.services

import dev.nhason.nivhasonfinalproject.data.models.placeIdModel.placeIdRespone
import dev.nhason.nivhasonfinalproject.data.models.placesModel.placesRespone
import dev.nhason.nivhasonfinalproject.data.models.weatherModel.WeatherResponse
import dev.nhason.nivhasonfinalproject.utils.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface Services {
    @GET("data/2.5/weather?units=metric")
   suspend fun getWeather(@Query("lat") lat: Double,
                          @Query("lon") lon: Double) : WeatherResponse


    @GET("maps/api/place/nearbysearch/json?radius=1500&type=restaurant")
    suspend fun getRestaurant(
        @Query("location") location: String
    ): placesRespone

    @GET("maps/api/place/details/json")
    suspend fun getRestaurantDetails(
        @Query("place_id") placeId: String
    ): placeIdRespone

    @GET("maps/api/place/nearbysearch/json?radius=1500&type=park")
    suspend fun getParks(
        @Query("location") location : String)
            : placesRespone

    @GET("maps/api/place/nearbysearch/json?radius=1500&type=parking")
    suspend fun getParking(
        @Query("location") location : String)
            : placesRespone

    companion object{
        fun create(
            baseURL: String,
            queryParam: String,
            queryValue: String,
        ):Services{
            val httpClient = OkHttpClient
                .Builder()
                .addInterceptor(TokenInterceptor(queryParam, queryValue))
                .build()

            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Services::class.java)
        }
    }
}