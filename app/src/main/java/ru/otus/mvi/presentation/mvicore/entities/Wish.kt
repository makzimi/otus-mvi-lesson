package ru.otus.mvi.presentation.mvicore.entities

sealed class Wish {
    object FeatureStarted: Wish()
    object SwipedToRefresh: Wish()
}