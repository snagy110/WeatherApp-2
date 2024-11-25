package com.telekommms.weatherapp.location

import android.content.Context
import android.location.Geocoder
import java.util.Locale

object GeocodeHelper {

    fun getLocalityFromCoordinates(
        longitude: Double,
        latitude: Double,
        context: Context
    ): String? {
        val addresses = Geocoder(
            context,
            Locale.ENGLISH
        ).getFromLocation(
            latitude,
            longitude,
            1
        )
        var locality = ""
        if (addresses?.isNotEmpty() == true) {
            locality = addresses?.get(0)?.locality.toString()
        }
        return locality
    }
}