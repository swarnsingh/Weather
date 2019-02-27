package com.swarn.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swarn.weather.R
import com.swarn.weather.data.Weather
import java.util.*

/**
 * @author Swarn Singh.
 */
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    @JvmField
    var weatherData: List<Weather>? = ArrayList()

    fun setWeatherData(data: List<Weather>?) {
        this.weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_content, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherData!!.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherData?.get(position)
        holder.weatherYearMonthTxtView.text = weather?.year.toString()
        holder.weatherValueTxtView.text = weather?.value.toString()
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val weatherYearMonthTxtView: TextView = itemView.findViewById(R.id.weather_month_year_txt_view)
        val weatherValueTxtView: TextView = itemView.findViewById(R.id.weather_value_txt_View)
    }
}