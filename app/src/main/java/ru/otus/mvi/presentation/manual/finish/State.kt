package ru.otus.mvi.presentation.manual.finish

import ru.otus.mvi.domain.RaMCharacter

data class State(
    val items: List<RaMCharacter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)