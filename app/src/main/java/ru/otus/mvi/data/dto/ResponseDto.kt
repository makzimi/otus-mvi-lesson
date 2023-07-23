package ru.otus.mvi.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: String,
)