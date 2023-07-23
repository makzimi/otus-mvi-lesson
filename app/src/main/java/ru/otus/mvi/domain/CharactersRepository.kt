package ru.otus.mvi.domain

import io.reactivex.Single

interface CharactersRepository {
    suspend fun getAllCharacters(): Result<List<RaMCharacter>>
    fun getAllCharactersSingle(): Single<List<RaMCharacter>>
}
