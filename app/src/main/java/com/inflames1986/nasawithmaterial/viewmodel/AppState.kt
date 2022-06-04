package com.inflames1986.nasawithmaterial.viewmodel

import com.inflames1986.nasawithmaterial.repository.dto.EarthEpicServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.MarsPhotosServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.PODServerResponseData
import com.inflames1986.nasawithmaterial.repository.dto.SolarFlareResponseData

sealed class AppState {
    data class SuccessPOD(val serverResponseData: PODServerResponseData) : AppState()
    data class SuccessEarthEpic (val serverResponseData: List<EarthEpicServerResponseData>) : AppState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData) : AppState()
    data class SuccessWeather(val solarFlareResponseData:List<SolarFlareResponseData>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}