package com.telekommms.weatherapp.network

import com.telekommms.library.weathersdk.models.Response
import com.telekommms.library.weathersdk.models.data.WeatherForecast
import com.telekommms.weatherapp.network.WeatherClient.client
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.util.InternalAPI
import io.ktor.utils.io.jvm.javaio.toInputStream
import kotlinx.serialization.json.Json

class WeatherRequestClient {

    @OptIn(InternalAPI::class)
    suspend fun requestWeatherForecast(
        latitude: Double,
        longitude: Double
    ): WeatherForecast {
        val response: HttpResponse =
            client.get("https://api.tomorrow.io/v4/weather/forecast?location=$latitude,$longitude&apikey=X4Ctxmi35fC8EL5EZe3L7rAETOIuXhCn")
        val inputAsString = response.content.toInputStream().bufferedReader().use { it.readText() }
        val obj = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            explicitNulls = false
            encodeDefaults = true
        }.decodeFromString<WeatherForecast>(inputAsString)
        return obj
    }
}