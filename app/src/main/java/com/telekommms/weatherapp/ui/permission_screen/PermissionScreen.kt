package com.telekommms.weatherapp.ui.permission_screen

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.weatherapp.permission.LocationPermission.requestLocationPermission
import com.telekommms.weatherapp.permission.LocationPermission.verifyLocationPermission
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.Screen
import com.telekommms.weatherapp.ui.theme.brightPurple
import com.telekommms.weatherapp.ui.theme.darkPurple
import com.telekommms.weatherapp.ui.theme.disabledTextColor
import com.telekommms.weatherapp.ui.theme.primaryColor
import com.telekommms.weatherapp.ui.theme.secondaryColor

@Composable
fun PermissionScreen(
    navigator: Navigator,
    activity: Activity
) {
    var permissionEnabled by remember {
        mutableStateOf(verifyLocationPermission(activity = activity))
    }
    Column(
        modifier = Modifier
            .background(primaryColor)
            .fillMaxSize()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 40.dp
            )
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
                modifier = Modifier
                    .height(24.dp)
                    .clickable {
                        navigator.goTo(Screen.Welcome)
//                    detailsActivity?.onBackPressedDispatcher?.onBackPressed()
                    }
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "Use my location", color = Color.White, fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.padding(20.dp))
        PermissionLabel()
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Location service",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if (permissionEnabled) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "",
                        tint = brightPurple
                    )
                    Text(text = "Okay", color = Color.White)
                }
            } else {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = brightPurple
                    )
                    Text(text = "Disabled", color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Location service",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if (permissionEnabled) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "",
                        tint = brightPurple
                    )
                    Text(text = "Okay", color = Color.White)
                }
            } else {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = brightPurple
                    )
                    Text(text = "Declined", color = Color.White)
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
                requestLocationPermission(activity = activity)
                permissionEnabled = verifyLocationPermission(activity)
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            border = BorderStroke(width = 1.dp, color = Color.White),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Check again", color = brightPurple)
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            onClick = {
                navigator.goTo(Screen.Home)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = brightPurple,
                disabledContainerColor = secondaryColor
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(),
            enabled = permissionEnabled
        ) {
            val textColor = if (permissionEnabled) darkPurple else disabledTextColor
            Text(text = "Done", color = textColor)
        }
    }
}