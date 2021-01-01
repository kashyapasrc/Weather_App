package com.kashyap.weather.domain.networking.response.forecast_info

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)