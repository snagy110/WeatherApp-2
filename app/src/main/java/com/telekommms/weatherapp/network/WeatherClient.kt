package com.telekommms.weatherapp.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging

object WeatherClient {

    val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.INFO
        }
    }
}