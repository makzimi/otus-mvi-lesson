package ru.otus.mvi.presentation.mvicore.entities

sealed class News {
    data class ErrorExecutingRequest(val throwable: Throwable) : News()
}