package com.telekommms.weatherapp.ui.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.weatherapp.ui.theme.brightPurple
import com.telekommms.weatherapp.ui.theme.primaryColor

@Composable
fun WeatherDetails(
    description: String,
    averageHumidity: String,
    humidity: Float,
    probability: String,
    uv: String,
    wind: String,
    visibility: String,
    pressure: String,
    sunrise: String,
    sunset: String
) {
    Column(
        modifier = Modifier.background(primaryColor)
    ) {
        Text(
            text = description,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Average humidity", color = Color.White, fontSize = 18.sp)
            Text(text = "$averageHumidity %", color = Color.White, fontSize = 18.sp)
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)), // Rounded edges
            progress = humidity,
            trackColor = brightPurple
        )
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Average probability of rain", color = Color.White, fontSize = 18.sp)
            Text(text = "$probability %", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Maximum UV index", color = Color.White, fontSize = 18.sp)
            Text(text = uv, color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Maximum wind speed", color = Color.White, fontSize = 18.sp)
            Text(text = "$wind km/h", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Average visibility", color = Color.White, fontSize = 18.sp)
            Text(text = "$visibility m", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Average air pressure", color = Color.White, fontSize = 18.sp)
            Text(text = "$pressure mbar", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sunrise", color = Color.White, fontSize = 18.sp)
            Text(text = "$sunrise Uhr", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.padding(15.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Sunset", color = Color.White, fontSize = 18.sp)
            Text(text = "$sunset Uhr", color = Color.White, fontSize = 16.sp)
        }
    }
}