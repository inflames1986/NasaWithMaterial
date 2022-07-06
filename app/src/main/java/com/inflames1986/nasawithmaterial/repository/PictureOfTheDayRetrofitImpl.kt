package com.inflames1986.nasawithmaterial.repository


import com.google.gson.GsonBuilder
import com.inflames1986.nasawithmaterial.utils.NASA_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PictureOfTheDayRetrofitImpl {
    private val nasaBaseUrl = NASA_BASE_URL
    fun getRetrofit():PictureOfTheDayAPI {
        val pictureOfTheDayRetrofit = Retrofit.Builder()
            .baseUrl(nasaBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return pictureOfTheDayRetrofit.create(PictureOfTheDayAPI::class.java)
    }
}