package ru.otus.mvi.presentation.manual.finish

import ru.otus.mvi.domain.RaMCharacter

sealed interface Change {
    object Loading : Change
    data class ListLoaded(val items: List<RaMCharacter>) : Change
    object Error: Change
    object ClearError: Change
}