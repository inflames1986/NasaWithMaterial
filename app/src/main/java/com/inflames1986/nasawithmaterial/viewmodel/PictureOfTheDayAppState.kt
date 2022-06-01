package com.inflames1986.nasawithmaterial.viewmodel

import com.inflames1986.nasawithmaterial.repository.PictureOfTheDayResponseData

sealed class PictureOfTheDayAppState{
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):PictureOfTheDayAppState()
    data class Error(val error:Throwable):PictureOfTheDayAppState()
    data class Loading(val progress:Int?):PictureOfTheDayAppState()
}