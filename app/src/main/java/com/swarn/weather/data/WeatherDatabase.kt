package com.swarn.weather.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @author Swarn Singh.
 */
@Database(entities = [Weather::class], version = 1, exportSchema = false)
@TypeConverters(WeatherDataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var instance: WeatherDatabase? = null

        private const val DATABASE_NAME = "Weather_Database"

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, WeatherDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance as WeatherDatabase
        }

        private val roomCallback = object : RoomDatabase.Callback() {
        }
    }
}