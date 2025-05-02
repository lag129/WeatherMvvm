package net.lag129.weathermvvm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.SubcomposeAsyncImage
import net.lag129.weathermvvm.utils.weatherString
import net.lag129.weathermvvm.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val weather by viewModel.weather.collectAsState()
    val reverseGeoCode by viewModel.reverseGeoCode.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        reverseGeoCode?.let {
            val geoCode = it.feature.firstOrNull()?.property?.addressElement
            Text(
                text = "${geoCode?.get(0)?.name}${geoCode?.get(1)?.name}の天気",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        weather?.let {
            SubcomposeAsyncImage(
                model = "https://openweathermap.org/img/wn/${it.weather.firstOrNull()?.icon}@4x.png",
                contentDescription = null,
                loading = { CircularProgressIndicator() },
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = weatherString(it.weather.firstOrNull()?.id),
                fontSize = 24.sp
            )
            Text(text = "${(it.main.temp - 273.15).toInt()}°C", fontSize = 24.sp)
            Text(text = "${it.main.humidity}%", fontSize = 20.sp)
        } ?: CircularProgressIndicator()
    }
}
