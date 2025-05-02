package net.lag129.weathermvvm

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import dagger.hilt.android.AndroidEntryPoint
import net.lag129.weathermvvm.ui.WeatherScreen
import net.lag129.weathermvvm.ui.theme.WeatherMvvmTheme
import net.lag129.weathermvvm.utils.PermissionHelper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>
    private lateinit var permissionHelper: PermissionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    Log.d("MainActivity", "Precise location access granted.")
                }

                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    Log.d("MainActivity", "Approximate location access granted.")
                }

                else -> {
                    Log.e("MainActivity", "No location access granted.")
                }
            }
        }

        permissionHelper = PermissionHelper(this, locationPermissionRequest)
        permissionHelper.requestPermissions()

        setContent {
            WeatherMvvmTheme {
                WeatherScreen()
            }
        }
    }
}
