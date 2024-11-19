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
import com.telekommms.weatherapp.MainActivity
import com.telekommms.weatherapp.ui.theme.primaryColor

@Composable
fun HomeScreen(
    activity: MainActivity
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
            LocationCard()
        }
        Spacer(modifier = Modifier.height(15.dp))
        DayCard(dayTitle = "Today", activity = activity, date = "18.11.2024")

        Spacer(modifier = Modifier.height(15.dp))
        DayCard(dayTitle = "Tomorrow", activity = activity, date = "19.11.2024")

        Spacer(modifier = Modifier.height(15.dp))
        DayCard(dayTitle = "Wednesday", activity = activity, date = "20.11.2024")

        Spacer(modifier = Modifier.height(15.dp))
        DayCard(dayTitle = "Thursday", activity = activity, date = "21.11.2024")
    }
}