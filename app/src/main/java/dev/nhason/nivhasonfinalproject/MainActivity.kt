package dev.nhason.nivhasonfinalproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.nhason.nivhasonfinalproject.databinding.ActivityMainBinding
import dev.nhason.nivhasonfinalproject.ui.restaurant.PlacesViewModel
import dev.nhason.nivhasonfinalproject.ui.weather.WeatherViewModel

import java.util.*


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navigationController: NavController
    private lateinit var client: FusedLocationProviderClient
    private val cts = CancellationTokenSource()
    private val permissionId = 2
    lateinit var weatherViewModel: WeatherViewModel
    lateinit var restaurantViewModel: PlacesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        restaurantViewModel = ViewModelProvider(this)[PlacesViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navigationController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navigationController)
        client = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        navigationController.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        showUserLocation()
    }

    @SuppressLint("MissingPermission")
    fun showUserLocation() {
        if (hasLocationPermission()) {
            if (isLocationEnabled()){
                val request = CurrentLocationRequest.Builder()
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .build()
                client.getCurrentLocation( request , cts.token).addOnSuccessListener {
                   val latLng = "${it.latitude},${it.longitude}"
                    weatherViewModel.getWeather(it.latitude,it.longitude)
                    restaurantViewModel.getRestaurant(latLng)
                }
            }else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
        }
        }else{
            requestPermissions()
        }
    }

    private fun hasLocationPermission(): Boolean =
        this.checkSelfPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

}