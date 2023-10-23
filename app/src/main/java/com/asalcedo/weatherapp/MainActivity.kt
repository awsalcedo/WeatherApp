package com.asalcedo.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asalcedo.weatherapp.data.network.WeatherApi
import com.asalcedo.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var weatherApi: WeatherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrentWeather()
    }

    private fun getCurrentWeather() {


        GlobalScope.launch(Dispatchers.IO) {
            val response = weatherApi.getCurrentWeather(
                "chicago",
                "metric",
                applicationContext.getString(R.string.api_key)
            )
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    binding.tvTemp.text = "Temperature: ${response.body()!!.main.temp}"
                }
            }
        }
    }
}