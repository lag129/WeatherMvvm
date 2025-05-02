package net.lag129.weathermvvm.data

import net.lag129.weathermvvm.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String = "current",
        @Query("appid") apiKey: String = BuildConfig.OPENWEATHER_API_KEY
    ): WeatherResponse
}