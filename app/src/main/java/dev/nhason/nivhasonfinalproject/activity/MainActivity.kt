package dev.nhason.nivhasonfinalproject.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dev.nhason.nivhasonfinalproject.R
import dev.nhason.nivhasonfinalproject.data.models.isFirstTime
import dev.nhason.nivhasonfinalproject.data.models.saveFirstTime
import dev.nhason.nivhasonfinalproject.databinding.ActivityMainBinding
import dev.nhason.nivhasonfinalproject.databinding.TermsDialogBinding
import dev.nhason.nivhasonfinalproject.services.NetworkBroadcast
import dev.nhason.nivhasonfinalproject.ui.PlacesViewModel
import dev.nhason.nivhasonfinalproject.ui.weather.WeatherViewModel
import java.util.*


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navigationController: NavController
    private lateinit var client: FusedLocationProviderClient
    private val cts = CancellationTokenSource()
    private val permissionId = 2
    lateinit var weatherViewModel: WeatherViewModel
    private lateinit var placesViewModel: PlacesViewModel
    private lateinit var broadcastReceiver: BroadcastReceiver



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        placesViewModel = ViewModelProvider(this)[PlacesViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        terms()
        navigationController = findNavController(R.id.nav_host_fragment_activity_main)
        NavigationUI.setupWithNavController(binding.navView,navigationController,false)
        client = LocationServices.getFusedLocationProviderClient(this)

        broadcastReceiver = NetworkBroadcast()
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    override fun onSupportNavigateUp(): Boolean {
        navigationController.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        getLocation()
    }

    private fun terms(){
        if (!isFirstTime){
            showTerms()
            saveFirstTime(true)
        }
    }

    private fun showTerms(){
        val dialogBinding = TermsDialogBinding.inflate(layoutInflater,binding.root,false)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()

        dialogBinding.editUsername.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.termsfeed.com/live/3f134c1c-a45b-48b2-ac2a-e2399f940cf4"))
            startActivity(browserIntent)
        }

        dialogBinding.buttonDone.setOnClickListener {
            dialog.dismiss()
        }
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                val request = CurrentLocationRequest.Builder()
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .build()
                client.getCurrentLocation(request, cts.token).addOnSuccessListener {
                    val latLng = "${it.latitude},${it.longitude}"
                    weatherViewModel.getWeather(it.latitude, it.longitude)
                    placesViewModel.getRestaurant(latLng)
                }
            } else {
            Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
    } else {
        requestPermissions()
    }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
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

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == permissionId) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

}