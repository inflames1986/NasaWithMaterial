package com.inflames1986.nasawithmaterial.repository

import com.inflames1986.nasawithmaterial.utils.GET_PICTURE_RETROFIT_ENDPOINT
import com.inflames1986.nasawithmaterial.utils.NASA_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET(GET_PICTURE_RETROFIT_ENDPOINT)
    fun getPictureOfTheDay(@Query(NASA_API_KEY) apiKey:String): Call<PictureOfTheDayResponseData>
}