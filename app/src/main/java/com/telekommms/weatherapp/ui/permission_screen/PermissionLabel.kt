package com.telekommms.weatherapp.ui.permission_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PermissionLabel() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Grant local authorisations",
        fontSize = 22.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.size(30.dp))
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "To be able to access your location, the app requires\nauthorisation to do so.\nYour phone must also support this.",
        fontSize = 14.sp,
        color = Color.White,
        textAlign = TextAlign.Start
    )
}