package com.swarn.weather.data

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.swarn.weather.util.AbsentLiveData

/**
 * @author Swarn Singh.
 */
@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo(name = "year")
    @field:SerializedName("year")
    var year: Int? = 0,

    @ColumnInfo(name = "month")
    @field:SerializedName("month")
    var month: Int? = 0,

    @ColumnInfo(name = "value")
    @field:SerializedName("value")
    var value: Double? = 0.0,

    @ColumnInfo(name = "data_type")
    var weatherDataType: String? = null,

    @ColumnInfo(name = "country")
    var country: String? = null
) {
    fun <T> ifExists(f: (String?, String?) -> LiveData<T>): LiveData<T> {
        return if (year == 0 || month == 0) {
            AbsentLiveData.create()
        } else {
            f(weatherDataType, country)
        }
    }
}