package com.telekommms.weatherapp.ui.details_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HighestLowest() {
    Row {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        Text(text = "Tomorrow")
    }
    Row {
        Column {
            Text(text = "")
            Text(text = "")
        }
        Column {
            Text(text = "")
            Text(text = "")
        }
    }
}