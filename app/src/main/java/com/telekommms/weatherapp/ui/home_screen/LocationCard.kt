package com.telekommms.weatherapp.ui.home_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.weatherapp.ui.theme.primaryColor
import com.telekommms.weatherapp.ui.theme.secondaryColor

@Composable
fun LocationCard(
    cityName: String
) {
    Card(
        modifier = Modifier
            .background(primaryColor)
            .padding(start = 10.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.White),
        colors = CardDefaults.cardColors(
                containerColor = primaryColor, //Card background color
        contentColor = Color.White  //Card content color,e.g.text
    ),
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
            ,
            text = cityName,
            fontSize = 16.sp
        )
    }
}