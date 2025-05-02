package net.lag129.weathermvvm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.lag129.weathermvvm.data.ReverseGeoCodeResponse
import net.lag129.weathermvvm.data.WeatherApi
import net.lag129.weathermvvm.data.WeatherResponse
import net.lag129.weathermvvm.data.YahooApi
import net.lag129.weathermvvm.utils.LocationHelper
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi,
    private val yahooApi: YahooApi,
    private val locationHelper: LocationHelper
) : ViewModel() {
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _reverseGeoCode = MutableStateFlow<ReverseGeoCodeResponse?>(null)
    val reverseGeoCode: StateFlow<ReverseGeoCodeResponse?> = _reverseGeoCode

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            try {
                val location = locationHelper.getCurrentLocation()
                if (location != null) {
                    val response = weatherApi.getWeather(
                        latitude = location.latitude,
                        longitude = location.longitude,
                    )
                    _weather.value = response
                    val reverseGeoCodeResponse = yahooApi.getReverseGeoCode(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                    println(reverseGeoCodeResponse)
                    _reverseGeoCode.value = reverseGeoCodeResponse
                }
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather", e)
            }
        }
    }
}
