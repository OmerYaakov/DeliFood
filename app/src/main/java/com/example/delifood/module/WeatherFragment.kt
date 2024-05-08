package com.example.delifood.module

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.R
import com.example.delifood.WeatherApi
import com.example.delifood.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFragment : Fragment() {

    private var tvDescription: TextView? = null
    private var tvTemperature: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvDescriptionValue: TextView? = null
    private var tvTemperatureValue: TextView? = null
    private var tvHumidityValue: TextView? = null
    private var tvCity: TextView? = null
    private var etCity: EditText? = null
    private var btnRefresh: Button? = null
    private var city: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        setupUI(view)
        btnRefresh?.setOnClickListener{
            Log.d("WeatherFragment", "Button clicked")
            makeWeatherApiCall()
        }

        return view
    }


    private fun setupUI(view: View){
        tvDescription = view.findViewById(R.id.tvDescription)
        tvTemperature = view.findViewById(R.id.tvTemp)
        tvHumidity = view.findViewById(R.id.tvHumidity)
        tvDescriptionValue = view.findViewById(R.id.tvValueDescription)
        tvTemperatureValue = view.findViewById(R.id.tvValueTemp)
        tvHumidityValue = view.findViewById(R.id.tvValueHumidity)
        tvCity = view.findViewById(R.id.tvCity)
        etCity = view.findViewById(R.id.etValueCity)
        btnRefresh = view.findViewById(R.id.btnRefresh)

    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun makeWeatherApiCall() {
        city = etCity?.text.toString()

        val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
        val call: Call<WeatherResponse> = weatherApi.getWeather(city!!, "0edd121b283109fbb921dab8b27663f3")
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        tvDescriptionValue?.text = weatherResponse.weather[0].description
                        val temp = weatherResponse.main.temp - 273.15
                        val formattedTemp = String.format("%.2f", temp)
                        tvTemperatureValue?.text = formattedTemp
                        tvHumidityValue?.text = weatherResponse.main.humidity.toString()

                        Log.d("WeatherFragment", "Weather response: $weatherResponse")
                    } else {
                        Log.e("WeatherFragment", "Weather response is null")
                    }
                } else {
                    val errorMessage = response.message()
                    Log.e("WeatherFragment", "Error: $errorMessage")
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}