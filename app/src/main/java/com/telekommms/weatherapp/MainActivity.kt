package com.telekommms.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.telekommms.library.weathersdk.WeatherClient
import com.telekommms.library.weathersdk.models.data.WeatherForecast
import com.telekommms.library.weathersdk.persistence.PlatformFileStore
import com.telekommms.library.weathersdk.persistence.PlatformSettingStore
import com.telekommms.weatherapp.ui.home_screen.HomeScreen
import com.telekommms.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity(), DeviceLocationTracker.DeviceLocationListener {
    private var lat = 0.0
    private var lon = 0.0
    private lateinit var deviceLocationTracker: DeviceLocationTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       val hasLocationPermission = (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED)

        if (!hasLocationPermission) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1
            )
        }

        deviceLocationTracker = DeviceLocationTracker(
            context = this,
            deviceLocationListener = this
        )
        val dataString = this.assets.open("weather_data.txt").bufferedReader().use {
                    it.readText()
                }
        val deserialized = Json.decodeFromString<WeatherForecast>(dataString)
        val platformFileStore = PlatformFileStore(this)
        val platformSettingStore = PlatformSettingStore(this)
        val weatherClient = WeatherClient(
            appId = "Weather App",
            platformSettingStore = platformSettingStore,
            platformFileStore = platformFileStore
        )
        CoroutineScope(Dispatchers.Default).launch {
            val weatherForecast = weatherClient.requestWeatherForecast(
                latitude = lat,
                longitude = lon
            )
            val weatherForecast2 = weatherForecast
            val realtimeWeather = weatherClient.requestRealtimeWeather(
                latitude = lat,
                longitude = lon
            )
            val realtimeWeather2 = realtimeWeather
            val realtimeHistoricalWeather = weatherClient.requestHistoricalWeather(
                latitude = lat,
                longitude = lon
            )
            val realtimeHistoricalWeather2 = realtimeHistoricalWeather
        }
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(this)
                }
            }
        }
    }

    override fun onDeviceLocationChanged(results: List<Address>?) {
        val currentLocation = results?.get(0)
        currentLocation?.apply {
            var currentAddressLine = String()
            for (i in 0..this.maxAddressLineIndex) {
                currentAddressLine += this.getAddressLine(i)
            }
            lat = latitude
            lon = longitude
            println("lat-lon $lat\n$lon")

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
        Greeting("Android")
    }
}