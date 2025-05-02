package net.lag129.weathermvvm.utils

fun weatherString(weatherId: Int?): String {
    return when (weatherId) {
        in 200..232 -> "雷雨"
        in 300..321 -> "霧雨"
        in 500..531 -> "雨"
        in 600..622 -> "雪"
        in 701..781 -> "霧"
        800 -> "晴れ"
        in 801..804 -> "くもり"
        else -> "Unknown"
    }
}
