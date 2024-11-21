package com.telekommms.weatherapp.ui.welcome_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.Screen
import com.telekommms.weatherapp.ui.theme.brightPurple
import com.telekommms.weatherapp.ui.theme.darkPurple
import com.telekommms.weatherapp.ui.theme.primaryColor

@Composable
fun WelcomeScreen(navigator: Navigator) {
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
        WelcomeLabel()
        Button(
            onClick = { navigator.goTo(Screen.EnterLocation) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            border = BorderStroke(width = 1.dp, color = Color.White)
        ) {
            Text(text = "Enter location", color = brightPurple)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
                navigator.goTo(Screen.Permission)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = brightPurple)
        ) {
            Text(text = "Use my location", color = darkPurple)
        }
    }
}