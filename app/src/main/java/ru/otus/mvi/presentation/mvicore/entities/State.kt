package ru.otus.mvi.presentation.mvicore.entities

import ru.otus.mvi.domain.RaMCharacter

data class State(
    val isLoading: Boolean = false,
    val items: List<RaMCharacter> = listOf()
)
