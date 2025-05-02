package net.lag129.weathermvvm.data

import net.lag129.weathermvvm.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface YahooApi {
    @GET("/geoapi/V1/reverseGeoCoder")
    suspend fun getReverseGeoCode(
        @Query("output") output: String = "json",
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.YAHOO_API_KEY
    ): ReverseGeoCodeResponse
}