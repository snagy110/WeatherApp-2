package com.telekommms.weatherapp

import android.location.Address
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.telekommms.library.weathersdk.models.Response
import com.telekommms.library.weathersdk.models.data.DataValuesDaily
import com.telekommms.library.weathersdk.models.data.DataValuesMinutely
import com.telekommms.library.weathersdk.models.data.Location
import com.telekommms.library.weathersdk.models.data.TimelineItem
import com.telekommms.library.weathersdk.models.data.WeatherForecast
import com.telekommms.library.weathersdk.models.data.WeatherForecastTimelines
import com.telekommms.weatherapp.location.GeocodeHelper.getLocalityFromCoordinates
import com.telekommms.weatherapp.network.WeatherRequestClient
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.Screen
import com.telekommms.weatherapp.ui.details_screen.DetailsScreen
import com.telekommms.weatherapp.ui.enter_location.EnterLocationScreen
import com.telekommms.weatherapp.ui.home_screen.HomeScreen
import com.telekommms.weatherapp.ui.permission_screen.PermissionScreen
import com.telekommms.weatherapp.ui.theme.WeatherAppTheme
import com.telekommms.weatherapp.ui.welcome_screen.WelcomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivity : ComponentActivity(), DeviceLocationTracker.DeviceLocationListener {
    private val screenFlow = MutableStateFlow<Screen>(Screen.Welcome)
    private val navigator: Navigator = object : Navigator {
        override fun goTo(screen: Screen) {
            screenFlow.value = screen
        }
    }
    private val lat = MutableStateFlow(0.0)
    private val lon = MutableStateFlow(0.0)
    private val city = MutableStateFlow("")
    private val minutelyForecast = MutableStateFlow<List<TimelineItem<DataValuesMinutely>>>(listOf())
    private val dailyForecast = MutableStateFlow<List<TimelineItem<DataValuesDaily>>>(listOf())
    private lateinit var deviceLocationTracker: DeviceLocationTracker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        deviceLocationTracker = DeviceLocationTracker(
            context = this,
            deviceLocationListener = this
        )

        val dataString = this.assets.open("weather_data.txt").bufferedReader().use {
            it.readText()
        }
        val weatherForecast = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<WeatherForecast>(dataString)
//        var weatherForecast = WeatherForecast(Location(), WeatherForecastTimelines(minutely = listOf(), hourly = listOf(), daily = listOf()))
//        CoroutineScope(Dispatchers.Default).launch {
//            val weatherRequestClient = WeatherRequestClient()
//            weatherForecast = weatherRequestClient.requestWeatherForecast(
//                latitude = lat.value,
//                longitude = lon.value
//            )
//        }
        dailyForecast.value = weatherForecast.timelines.daily
        minutelyForecast.value = weatherForecast.timelines.minutely

        setContent {
            val screen by screenFlow.collectAsState()
            val minutely by minutelyForecast.collectAsState()
            val latitudeValue by lat.collectAsState()
            val longitudeValue by lon.collectAsState()

            if (screen !is Screen.Home) {
                BackHandler {
                    when (screen) {
                        Screen.Home -> {
                            /* No-op */
                        }

                        else -> navigator.goTo(Screen.Home)
                    }
                }
            }
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
            val c: Calendar = Calendar.getInstance()
            val dateformat = SimpleDateFormat("dd-MMM-yyyy hh:mm")
            val datetime: String = dateformat.format(c.getTime())
            println("datetime" + Clock.System.now().toJavaInstant())
            WeatherAppTheme {
                var weatherScreen by remember {
                    mutableIntStateOf(-1)
                }
                var detailsScreen by remember {
                    mutableIntStateOf(0)
                }
                PermissionScreen(navigator = navigator, activity = this)
                AnimatedContent(targetState = screen, label = "main") { targetScreen ->
                    when (targetScreen) {
                        Screen.Home -> HomeScreen(navigator, cityName = getLocalityFromCoordinates(longitude = longitudeValue, latitude = latitudeValue, this@MainActivity) ?: "", weatherForecast.timelines.daily, onSetDayItem = { detailsScreen = it })
                        Screen.Details -> DetailsScreen(navigator = navigator, dayTimelineItem =  weatherForecast.timelines.daily[detailsScreen], dayIndex = detailsScreen, minutely = minutely)
                        Screen.Permission -> PermissionScreen(navigator = navigator, activity = this@MainActivity)
                        Screen.Welcome -> WelcomeScreen(navigator = navigator)
                        Screen.EnterLocation -> EnterLocationScreen(navigator = navigator, onSetCoordinates = { latitude, longitude ->
                            lat.value = latitude
                            lon.value = longitude
                        })
                    }
                }
            }
        }
    }

    // Budapest: 37.421998,-122.084000
    override fun onDeviceLocationChanged(results: List<Address>?) {
        val currentLocation = results?.get(0)
        currentLocation?.apply {
            var currentAddressLine = String()
            for (i in 0..this.maxAddressLineIndex) {
                currentAddressLine += this.getAddressLine(i)
            }
            city.value = locality
            lat.value = latitude
            lon.value = longitude

        }
    }
}