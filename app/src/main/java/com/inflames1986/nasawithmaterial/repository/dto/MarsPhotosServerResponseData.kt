package com.inflames1986.nasawithmaterial.repository.dto

import com.example.nasaapp.model.data.MarsServerResponseData
import com.google.gson.annotations.SerializedName

data class MarsPhotosServerResponseData(
    @field:SerializedName("photos") val photos: ArrayList<MarsServerResponseData>,
)