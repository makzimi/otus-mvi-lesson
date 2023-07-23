package ru.otus.mvi.presentation.roxie.mvi

import com.ww.roxie.BaseState
import ru.otus.mvi.domain.RaMCharacter

data class State(
    val items: List<RaMCharacter> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
): BaseState