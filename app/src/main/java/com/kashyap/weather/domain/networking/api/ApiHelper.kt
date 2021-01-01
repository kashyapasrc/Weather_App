package com.mindorks.example.coroutines.data.api

import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate

interface ApiHelper {

    suspend fun getWeatherUpdates(params: HashMap<String, String>): WeatherUpdate

    suspend fun getForecastInfo(params: HashMap<String, String>): ForecastInfo


}