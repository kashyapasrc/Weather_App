package com.mindorks.example.coroutines.data.api

import com.kashyap.weather.domain.networking.api.Client
import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate

class ApiHelperImpl(private val apiService: Client) : ApiHelper {

    override suspend fun getWeatherUpdates(params: HashMap<String, String>): WeatherUpdate =
        apiService.getWeatherUpdates(params = params)

    override suspend fun getForecastInfo(params: HashMap<String, String>): ForecastInfo {
        return apiService.getForecastInfo(params = params);
    }


}