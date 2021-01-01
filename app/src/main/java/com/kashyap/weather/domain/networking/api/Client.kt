package com.kashyap.weather.domain.networking.api

import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Client {
    //http://api.openweathermap.org/data/2.5/weather?lat=16.1766266737351&lon=81.130277775228&appid=fae7190d7e6433ec3a45285ffcf55c86

    @GET("weather")
    suspend fun getWeatherUpdates(@QueryMap() params: HashMap<String, String>): WeatherUpdate

    @GET("forecast")
    suspend fun getForecastInfo(@QueryMap() params: HashMap<String, String>): ForecastInfo
}