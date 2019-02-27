package com.swarn.weather.api

import androidx.lifecycle.LiveData
import com.swarn.weather.data.Weather
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Swarn Singh.
 */
interface WeatherService {

    @GET("{type}-{country}.json")
    fun getWeatherData(@Path("type") type: String, @Path("country") country: String): LiveData<ApiResponse<List<Weather>>>
}