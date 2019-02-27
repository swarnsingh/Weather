package com.swarn.weather.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swarn.weather.data.Weather
import com.swarn.weather.repository.Resource
import com.swarn.weather.repository.WeatherRepository

/**
 * @author Swarn Singh.
 */
class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepository: WeatherRepository?
    private val _weatherData: MutableLiveData<Weather> = MutableLiveData()

    val data: LiveData<Weather>
        get() = _weatherData

    init {
        weatherRepository = WeatherRepository(application)
    }

    fun getWeatherReport(type: String, country: String): LiveData<Resource<List<Weather>>> {
        return weatherRepository!!.fetchWeatherData(type, country)
    }
}