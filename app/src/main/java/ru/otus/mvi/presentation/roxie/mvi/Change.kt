package ru.otus.mvi.presentation.roxie.mvi

import ru.otus.mvi.domain.RaMCharacter

sealed class Change {
    object Loading : Change()
    data class ListLoaded(val items: List<RaMCharacter>) : Change()
    object Error: Change()
    object ClearError: Change()
}