package com.telekommms.weatherapp

import java.text.SimpleDateFormat
import java.util.Date

object DayHelper {
    fun getDayName(): String {
        val sdf: SimpleDateFormat = SimpleDateFormat("EEEE")
        return sdf.format(Date())
    }
}