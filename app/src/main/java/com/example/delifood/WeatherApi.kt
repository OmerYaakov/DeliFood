package com.example.delifood

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}
