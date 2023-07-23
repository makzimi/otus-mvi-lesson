package ru.otus.mvi.presentation.mvicore.entities

import ru.otus.mvi.domain.RaMCharacter

sealed class Effect {
    object StartedLoading : Effect()
    data class ItemsLoaded(val items: List<RaMCharacter>) : Effect()
    data class ErrorLoading(val throwable: Throwable) : Effect()
}