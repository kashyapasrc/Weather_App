package com.kashyap.weather.domain.networking.response.forecast_info

data class ForecastInfo(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Forecast>,
    val message: Int
)