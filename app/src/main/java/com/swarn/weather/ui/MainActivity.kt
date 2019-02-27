package com.swarn.weather.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swarn.weather.R
import com.swarn.weather.adapter.WeatherAdapter
import com.swarn.weather.repository.Status
import com.swarn.weather.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        weatherRecyclerView = findViewById(R.id.weather_recycler_view)
        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.setHasFixedSize(true)

        weatherAdapter = WeatherAdapter()
        weatherRecyclerView.adapter = weatherAdapter

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        weatherViewModel
            .getWeatherReport("Rainfall", "England")
            .observe(this, Observer {
                when {
                    it.status == Status.LOADING -> showProgressDialog()
                    it.status == Status.SUCCESS -> {
                        hideProgressDialog()
                        weatherAdapter.setWeatherData(it?.data)
                    }
                    else -> hideProgressDialog()
                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
