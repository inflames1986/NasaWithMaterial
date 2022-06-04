package ru.geekbrains.nasaapi.repository

import com.google.gson.GsonBuilder
import com.inflames1986.nasawithmaterial.repository.dto.EarthEpicServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.MarsPhotosServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.PODServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.SolarFlareResponseData
import com.inflames1986.nasawithmaterial.utils.NASA_BASE_URL
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl {


    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(RetrofitApi::class.java)
    }

    fun getPictureOfTheDay(
        apiKey: String,
        date: String,
        podCallback: Callback<PODServerResponseData>
    ) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }

    // Earth Polychromatic Imaging Camera
    fun getEPIC(apiKey: String, epicCallback: Callback<List<EarthEpicServerResponseData>>) {
        api.getEPIC(apiKey).enqueue(epicCallback)
    }

    fun getMarsPictureByDate(
        earth_date: String,
        apiKey: String,
        marsCallbackByDate: Callback<MarsPhotosServerResponseData>
    ) {
        api.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }

    fun getSolarFlare(
        apiKey: String,
        podCallback: Callback<List<SolarFlareResponseData>>,
        startDate: String = "2021-09-07"
    ) {
        api.getSolarFlare(apiKey, startDate).enqueue(podCallback)
    }
}
