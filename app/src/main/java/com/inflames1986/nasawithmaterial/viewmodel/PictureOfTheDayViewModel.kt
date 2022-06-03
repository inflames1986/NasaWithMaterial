package com.inflames1986.nasawithmaterial.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inflames1986.nasawithmaterial.BuildConfig
import com.inflames1986.nasawithmaterial.repository.PictureOfTheDayResponseData
import com.inflames1986.nasawithmaterial.repository.PictureOfTheDayRetrofitImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayAppState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<PictureOfTheDayAppState> {
        return liveData
    }

    fun sendRequest() {
        liveData.postValue(PictureOfTheDayAppState.Loading(null))
        pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(callback)
    }

    fun sendServerRequest(date:String) {
        liveData.value = PictureOfTheDayAppState.Loading(0)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            liveData.value = PictureOfTheDayAppState.Error(Throwable("wrong key"))
        } else {
            pictureOfTheDayRetrofitImpl.getRetrofit().getPictureOfTheDay(apiKey,date).enqueue(callback)
        }
    }

    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                response.body()?.let {
                    liveData.postValue(PictureOfTheDayAppState.Success(it))
                }
            } else {
                Log.d("@@@", response.message())
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            try {

            } catch (e: IllegalStateException) {
                Log.d("@@@", e.toString())
            }
        }
    }
}