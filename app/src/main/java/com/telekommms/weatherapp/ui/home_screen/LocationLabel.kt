package com.telekommms.weatherapp.ui.home_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun LocationLabel() {
    Text(
        modifier = Modifier,
        text = "Location",
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}