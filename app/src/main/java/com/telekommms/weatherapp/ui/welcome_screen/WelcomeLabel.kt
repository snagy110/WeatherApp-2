package com.telekommms.weatherapp.ui.welcome_screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeLabel() {
    Spacer(modifier = Modifier.size(80.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Welcome to the weather app!",
        fontSize = 22.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.size(30.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "To be able to start, we need a location for which the\nweather information can be retrieved.",
        fontSize = 14.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.size(30.dp))
}