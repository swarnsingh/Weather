package com.swarn.weather.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.swarn.weather.AppExecutors
import com.swarn.weather.api.APIClient
import com.swarn.weather.api.ApiResponse
import com.swarn.weather.api.WeatherService
import com.swarn.weather.data.Weather
import com.swarn.weather.data.WeatherDao
import com.swarn.weather.data.WeatherDatabase
import kotlin.streams.toList

/**
 * @author Swarn Singh.
 */
class WeatherRepository(application: Application) : Repository<Weather> {


    private var weatherDao: WeatherDao
    private lateinit var data: LiveData<Resource<List<Weather>>>
    private var weatherService: WeatherService

    init {
        val database = WeatherDatabase.getInstance(application)
        weatherDao = database.weatherDao()
        weatherService = APIClient.createService(WeatherService::class.java)
    }

    override fun insert(item: Weather) {
        weatherDao.insert(item)
    }

    fun insertAll(items: List<Weather>) {
        weatherDao.insertAll(items)
    }

    override fun update(item: Weather) {
        weatherDao.update(item)
    }

    override fun delete(item: Weather) {
        weatherDao.delete(item)
    }


    fun fetchWeatherData(type: String, country: String): LiveData<Resource<List<Weather>>> {
        return object : NetworkBoundResource<List<Weather>, List<Weather>>(AppExecutors) {
            override fun saveCallResult(item: List<Weather>) {
                item.stream()
                    .peek { weather ->
                        run {
                            weather.weatherDataType = type
                            weather.country = country
                        }
                    }.toList()

                insertAll(item)
            }

            override fun shouldFetch(data: List<Weather>?) = data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<Weather>> {
                return weatherDao.fetchAll(type, country)
            }

            override fun createCall(): LiveData<ApiResponse<List<Weather>>> {
                return weatherService.getWeatherData(type, country)
            }
        }.asLiveData()
    }

}