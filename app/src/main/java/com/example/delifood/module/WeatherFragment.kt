package com.example.delifood.module

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.delifood.R

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        setupUI()
        return view
    }


    private fun setupUI(){
        tvDescription = view?.findViewById(R.id.tvDescription)
        tvTemperature = view?.findViewById(R.id.tvTemp)
        tvHumidity = view?.findViewById(R.id.tvHumidity)
        tvDescriptionValue = view?.findViewById(R.id.tvDescription)
        tvTemperatureValue = view?.findViewById(R.id.tvValueTemp)
        tvHumidityValue = view?.findViewById(R.id.tvValueHumidity)
        tvCity = view?.findViewById(R.id.tvCity)
        etCity = view?.findViewById(R.id.etValueCity)
        btnRefresh = view?.findViewById(R.id.btnRefresh)
    }

}