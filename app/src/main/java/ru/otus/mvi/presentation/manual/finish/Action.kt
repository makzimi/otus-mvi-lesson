package ru.otus.mvi.presentation.manual.finish


sealed interface Action {
    object FeatureStarted : Action
    object SwipedToRefresh : Action
    object ErrorShown : Action
}