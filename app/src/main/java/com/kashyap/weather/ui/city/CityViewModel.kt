package com.kashyap.weather.ui.city

import android.app.Application
import androidx.lifecycle.*
import com.kashyap.weather.domain.constants.ApiStringConstants
import com.kashyap.weather.domain.constants.UrlConstant
import com.kashyap.weather.domain.models.BookMarkModel
import com.kashyap.weather.domain.networking.response.forecast_info.ForecastInfo
import com.kashyap.weather.domain.networking.response.weather_update.WeatherUpdate
import com.kashyap.weather.domain.repository.WeatherRepository
import com.kashyap.weather.ui.base.BaseViewModel
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.launch

class CityViewModel(application: Application) : BaseViewModel(application) {

    private val weatherUpdates = MutableLiveData<Resource<WeatherUpdate>>()
    private val forecastInfo = MutableLiveData<Resource<ForecastInfo>>()
    private lateinit var repo: WeatherRepository

    init {
        repo = WeatherRepository.getInstance(application)
    }

    fun fetchWeatherUpdates(bookMarkModel: BookMarkModel) {
        viewModelScope.launch {
            weatherUpdates.postValue(Resource.loading(null))
            try {
                val params = HashMap<String, String>()
                params.put(ApiStringConstants.LATITUDE, bookMarkModel.latitude.toString())
                params.put(ApiStringConstants.LONGITUDE, bookMarkModel.longitude.toString())
                params.put(ApiStringConstants.APP_ID, UrlConstant.APP_ID)

                val weatherUpdatesFromApi = repo.fetchWeatherUpdates(params);
                weatherUpdates.postValue(Resource.success(weatherUpdatesFromApi))

            } catch (e: Exception) {
                weatherUpdates.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun fetchForecastInfo(bookMarkModel: BookMarkModel) {
        viewModelScope.launch {
            forecastInfo.postValue(Resource.loading(null))
            try {
                val params = HashMap<String, String>()
                params.put(ApiStringConstants.LATITUDE, bookMarkModel.latitude.toString())
                params.put(ApiStringConstants.LONGITUDE, bookMarkModel.longitude.toString())
                val days = "5";
             //   params.put(ApiStringConstants.COUNT, days)
                params.put(ApiStringConstants.APP_ID, UrlConstant.APP_ID)


                val foreCastInfoFromApi = repo.fetchForecastInfo(params);
                forecastInfo.postValue(Resource.success(foreCastInfoFromApi))

            } catch (e: Exception) {
                forecastInfo.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getWeatherUpdates(): LiveData<Resource<WeatherUpdate>> {
        return weatherUpdates
    }

    fun getForecastInfo(): LiveData<Resource<ForecastInfo>> {
        return forecastInfo
    }


}