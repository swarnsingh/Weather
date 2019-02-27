package com.swarn.weather.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * @author Swarn Singh.
 */
@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Weather)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Weather>)

    @Update
    fun update(item: Weather)

    @Delete
    fun delete(item: Weather)

    @Query("DELETE FROM weather")
    fun deleteAll()

    @Query("SELECT * FROM weather WHERE data_type =  :type AND country = :country")
    fun fetchAll(type: String, country: String): LiveData<List<Weather>>
}