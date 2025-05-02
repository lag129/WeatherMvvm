package net.lag129.weathermvvm.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LocationHelper(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            val locationTask: Task<Location> = fusedLocationClient.lastLocation

            locationTask.addOnSuccessListener { location ->
                if (location != null) {
                    continuation.resume(location)
                } else {
                    continuation.resumeWithException(Exception("位置情報が取得できませんでした"))
                }
            }.addOnFailureListener { exception ->
                Log.e("LocationHelper", "位置情報取得エラー: ${exception.message}")
                continuation.resumeWithException(exception)
            }
        }
    }
}