package com.kashyap.weather.domain.networking.response.weather_update

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)