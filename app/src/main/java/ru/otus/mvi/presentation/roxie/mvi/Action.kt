package ru.otus.mvi.presentation.roxie.mvi

import com.ww.roxie.BaseAction

sealed class Action : BaseAction {
    object LoadCharacters : Action()
    object ErrorShown : Action()
}