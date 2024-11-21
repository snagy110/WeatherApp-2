package com.telekommms.weatherapp.ui.enter_location

import android.location.Address
import android.location.Geocoder
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.telekommms.weatherapp.ui.Navigator
import com.telekommms.weatherapp.ui.Screen
import com.telekommms.weatherapp.ui.theme.brightPurple
import com.telekommms.weatherapp.ui.theme.darkPurple
import com.telekommms.weatherapp.ui.theme.disabledTextColor
import com.telekommms.weatherapp.ui.theme.primaryColor
import com.telekommms.weatherapp.ui.theme.secondaryColor
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterLocationScreen(
    navigator: Navigator,
    onSetCoordinates: (latitude: Double, longitude: Double) -> Unit
) {
    val context = LocalContext.current
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
        Spacer(modifier = Modifier.padding(20.dp))
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
                text = "Enter location", color = Color.White, fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        EnterLocationLabel()
        Spacer(modifier = Modifier.size(30.dp))
        var locationInput by remember { mutableStateOf("") }
        var doneButtonEnabled by remember { mutableStateOf(false) }
        var addressList by remember { mutableStateOf(listOf<Address>()) }

        OutlinedTextField(
            value = locationInput,
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newValue ->
                locationInput = newValue
                Geocoder(
                    context,
                    Locale.ENGLISH
                ).getFromLocationName(locationInput, 2)?.let { addresses ->
                    addressList = addresses
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            trailingIcon = {
                if (locationInput != "") {
                    IconButton(
                        onClick = { locationInput = "" },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = primaryColor)
                    ) {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear Text",
                            tint = Color.White
                        )
                    }
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = brightPurple,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = brightPurple,
                focusedTextColor = Color.White
            )
        )
        Spacer(modifier = Modifier.size(30.dp))
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
            enabled = doneButtonEnabled
        ) {
            val textColor = if (doneButtonEnabled) darkPurple else disabledTextColor
            Text(text = "Done", color = textColor)
        }
        Spacer(modifier = Modifier.size(30.dp))
        addressList.forEach {
            Button(
                onClick = {
                    locationInput = it.locality
                    onSetCoordinates(it.latitude, it.longitude)
                    doneButtonEnabled = true
                },
                colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
                border = BorderStroke(width = 1.dp, color = Color.White),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 22.sp
                        )
                    ) {
                        append(it.locality)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    ) {
                        append("\n${it.postalCode} - ${it.adminArea} - ${it.countryName}")
                    }
                },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
//        TextField(
//            value = text.value,
//            onValueChange = { text.value = it },
//            label = { Text("Search") },
//            placeholder = { Text("Type here...") },
//            singleLine = true,
//            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
//            trailingIcon = {
//                if (text.value.isNotEmpty()) {
//                    IconButton(onClick = { text.value = "" }) {
//                        Icon(Icons.Filled.Clear, contentDescription = "Clear Text")
//                    }
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth(),
//        )

    }
}