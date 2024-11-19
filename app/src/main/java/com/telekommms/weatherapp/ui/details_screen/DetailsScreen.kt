package com.telekommms.weatherapp.ui.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.telekommms.weatherapp.DetailsActivity
import com.telekommms.weatherapp.ui.theme.primaryColor

@Composable
fun DetailsScreen(
    detailsActivity: DetailsActivity
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .background(primaryColor)
            .padding(10.dp)
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
                modifier = Modifier.height(24.dp).clickable { detailsActivity?.onBackPressedDispatcher?.onBackPressed() }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Tomorrow", color = Color.White, fontSize = 24.sp)
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 80.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 32.sp
                        )
                    ) {
                        append("C\n")
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
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 26.sp
                        )
                    ) {
                        append("C\n")
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
        Spacer(modifier = Modifier.size(20.dp).padding(top = 20.dp))
        WeatherDetails(
            description = "description",
            averageHumidity = "averageHumidity",
            humidity = 75f,
            probability = "probability",
            uv = "uv",
            wind = "wind",
            visibility = "visibility",
            pressure = "pressure",
            sunrise = "sunrise",
            sunset = "sunset"
        )
    }

}