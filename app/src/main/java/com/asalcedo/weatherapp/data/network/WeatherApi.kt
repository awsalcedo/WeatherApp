package com.asalcedo.weatherapp.data.network

import com.asalcedo.weatherapp.data.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/****
 * Project: WeatherApp
 * From: com.asalcedo.weatherapp.data.network
 * Created by Alex Salcedo Silva on 20/10/23 at 22:40
 * All rights reserve 2022.
 ***/
interface WeatherApi {

    @GET("weather?")
    suspend fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Response<CurrentWeather>
}