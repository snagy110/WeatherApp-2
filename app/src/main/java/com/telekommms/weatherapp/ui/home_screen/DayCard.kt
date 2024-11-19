package com.telekommms.weatherapp.ui.home_screen

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.weatherapp.DetailsActivity
import com.telekommms.weatherapp.MainActivity
import com.telekommms.weatherapp.ui.theme.secondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayCard(
    dayTitle: String,
    activity: MainActivity,
    date: String
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = secondaryColor, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(width = 1.dp, color = Color.White),
        onClick = {
            context.startActivity(Intent(activity, DetailsActivity::class.java))
        }
    ) {
        Text(
            modifier = Modifier
                .padding(start = 20.dp, top = 15.dp),
            text = dayTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 15.dp),
            text = date,
            fontSize = 18.sp
        )
    }
}