package com.telekommms.weatherapp.ui.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.telekommms.library.weathersdk.models.data.DataValuesDaily
import com.telekommms.library.weathersdk.models.data.TimelineItem
import com.telekommms.weatherapp.MainActivity
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.theme.primaryColor

@Composable
fun HomeScreen(
    navigator: Navigator,
    cityName: String,
    daily: List<TimelineItem<DataValuesDaily>>,
    onSetDayItem: (dayItem: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(primaryColor)
            .padding(10.dp)
            .fillMaxSize()
    ) {
        WeatherForecastLabel()
        Spacer(modifier = Modifier.height(45.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            LocationLabel()
            LocationCard(cityName = cityName)
        }

        daily.forEachIndexed { index, dailyItem ->
            Spacer(modifier = Modifier.height(15.dp))
            DayCard(navigator = navigator, dayTimelineItem = dailyItem, dayIndex = index,
                onSetDayItem = { onSetDayItem(it) }
            )
        }
    }
}