package com.kashyap.weather.domain.networking.response.weather_update

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)