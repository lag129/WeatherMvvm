package net.lag129.weathermvvm.data

import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>
)

@Serializable
data class Main(
    val temp: Float,
    val humidity: Int
)

@Serializable
data class Weather(
    val id: Int,
    val description: String,
    val icon: String
)
