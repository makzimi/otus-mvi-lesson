package ru.otus.mvi.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.mvi.data.dto.RAMResponseDto

interface RAMRetrofitService {

    companion object {
        const val ENDPOINT = "https://rickandmortyapi.com/api/"
    }

    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int = 0): RAMResponseDto

    @GET("character/")
    fun getCharactersSingle(@Query("page") page: Int = 0): Single<RAMResponseDto>
}