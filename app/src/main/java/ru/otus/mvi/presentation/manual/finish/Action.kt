package ru.otus.mvi.presentation.manual.finish


sealed interface Action {
    object LoadCharacters : Action
    object ErrorShown : Action
}