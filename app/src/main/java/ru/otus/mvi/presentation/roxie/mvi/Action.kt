package ru.otus.mvi.presentation.roxie.mvi

import com.ww.roxie.BaseAction

sealed class Action : BaseAction {
    object FeatureStarted : Action()
    object SwipedToRefresh: Action()
    object ErrorShown : Action()
}