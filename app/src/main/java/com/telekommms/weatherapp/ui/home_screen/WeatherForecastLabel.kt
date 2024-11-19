package com.telekommms.weatherapp.ui.home_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecastLabel() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Weather forecast",
        fontSize = 24.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}