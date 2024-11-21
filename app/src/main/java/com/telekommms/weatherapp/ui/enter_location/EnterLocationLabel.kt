package com.telekommms.weatherapp.ui.enter_location

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
fun EnterLocationLabel() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Search your location",
        fontSize = 22.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.size(30.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Search for your location in the following text field and then\nselect it from the list.",
        fontSize = 12.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
}