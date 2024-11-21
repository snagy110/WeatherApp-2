package com.telekommms.weatherapp.ui.details_screen

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.library.weathersdk.models.data.DataValuesDaily
import com.telekommms.library.weathersdk.models.data.DataValuesMinutely
import com.telekommms.library.weathersdk.models.data.TimelineItem
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.Screen
import com.telekommms.weatherapp.ui.theme.primaryColor
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaInstant
import kotlinx.datetime.toLocalDateTime

@Composable
fun DetailsScreen(
    dayTimelineItem: TimelineItem<DataValuesDaily>,
    dayIndex: Int,
    navigator: Navigator,
    minutely: List<TimelineItem<DataValuesMinutely>>
) {
//    val dayMinutely = mutableListOf<TimelineItem<DataValuesMinutely>>()
//    val minutelyNow = mutableListOf<TimelineItem<DataValuesMinutely>>()
//    minutely.forEach {
//        if (it.time.toString().substring(0..9) == dayTimelineItem.time.toString()
//                .substring(0..9)
//        ) {
//            dayMinutely.add(it)
//        }
//    }
//    dayMinutely.forEach {
//        val one = it.time.toString().substring(0..15)
//        val two = Clock.System.now().toJavaInstant().toString().substring(0..15)
//        if (it.time.toString().substring(0..15) == "2024-11-21T10:22") {
//            minutelyNow.add(it)
//        }
//    }
    Column(
        modifier = Modifier
            .background(primaryColor)
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 20.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .height(24.dp)
                    .clickable {
                        navigator.goTo(Screen.Home)
//                    detailsActivity?.onBackPressedDispatcher?.onBackPressed()
                    }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = when (dayIndex) {
                    0 -> {
                        "Today"
                    }

                    1 -> {
                        "Tomorrow"
                    }

                    else -> {
                        dayTimelineItem.time.toLocalDateTime(TimeZone.UTC).dayOfWeek.toString()
                    }
                }, color = Color.White, fontSize = 24.sp
            )
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 80.dp)
        ) {
            val temperatureMax: String =
                java.lang.String.valueOf(dayTimelineItem.values.temperatureMax.toInt()) + " \u2103"
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 32.sp
                        )
                    ) {
                        append("$temperatureMax\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    ) {
                        append("Highest value")
                    }
                },
                color = Color.White
            )
            val temperatureMin: String =
                java.lang.String.valueOf(dayTimelineItem.values.temperatureMin.toInt()) + " \u2103"
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    ) {
                        append("$temperatureMin\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    ) {
                        append("Lowest value")
                    }
                },
                color = Color.White,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .size(20.dp)
                .padding(top = 20.dp)
        )
        WeatherDetails(
            description = "Mostly Sunny",
            averageHumidity = dayTimelineItem.values.humidityAvg.toInt().toString(),
            humidity = dayTimelineItem.values.humidityAvg.toFloat(),
            rainProbability = dayTimelineItem.values.precipitationProbabilityAvg.toInt().toString(),
//            uv = minutelyNow[0].values.uvIndex.toString(),
//            wind = minutelyNow[0].values.windSpeed.toInt().toString(),
//            visibility = minutelyNow[0].values.visibility.toInt().toString(),
//            pressure = minutelyNow[0].values.pressureSurfaceLevel.toInt().toString(),
            uv = minutely[0].values.uvIndex.toString(),
            wind = minutely[0].values.windSpeed.toInt().toString(),
            visibility = minutely[0].values.visibility.toInt().toString(),
            pressure = minutely[0].values.pressureSurfaceLevel.toInt().toString(),
            sunrise = "${dayTimelineItem.values.sunriseTime?.toLocalDateTime(TimeZone.UTC)?.hour}:${
                dayTimelineItem.values.sunriseTime?.toLocalDateTime(
                    TimeZone.UTC
                )?.minute
            }",
            sunset = "${dayTimelineItem.values.sunsetTime?.toLocalDateTime(TimeZone.UTC)?.hour}:${
                dayTimelineItem.values.sunsetTime?.toLocalDateTime(
                    TimeZone.UTC
                )?.minute
            }"
        )
    }
}