package com.kashyap.weather.domain.repository

import android.content.Context
import com.kashyap.weather.domain.databases.DatabaseBuilder
import com.kashyap.weather.domain.databases.DatabaseHelper
import com.kashyap.weather.domain.databases.DatabaseHelperImpl
import com.kashyap.weather.domain.networking.api.ClientCreator
import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate
import com.mindorks.example.coroutines.data.api.ApiHelper
import com.mindorks.example.coroutines.data.api.ApiHelperImpl

class WeatherRepository {


    companion object {
        private lateinit var dbHelper: DatabaseHelper
        private lateinit var apiHelper: ApiHelper
        private var instance: WeatherRepository? = null;

        fun getInstance(context: Context): WeatherRepository {
            if (instance == null) {
                instance = WeatherRepository()
                dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context))

                apiHelper = ApiHelperImpl(ClientCreator.client)
            }
            return instance as WeatherRepository;
        }
    }

    suspend fun fetchWeatherUpdates(params: HashMap<String, String>): WeatherUpdate {
        return apiHelper.getWeatherUpdates(params)
    }

    suspend fun fetchForecastInfo(params: HashMap<String, String>): ForecastInfo {
        return apiHelper.getForecastInfo(params)
    }

}