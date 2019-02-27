package com.swarn.weather.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * @author Swarn Singh.
 */
object WeatherDataConverter {

    val gson = Gson()

    @TypeConverter
    fun listToValue(items: List<Weather>?): String? {
        return gson.toJson(items)
    }

    @TypeConverter
    fun fromValueToList(value: String?): List<Weather>? {
        if (value == null) {
            return null
        }
        val type = object : TypeToken<List<Weather>>() {

        }.type
        return gson.fromJson(value, type)
    }
}