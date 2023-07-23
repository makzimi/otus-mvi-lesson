package ru.otus.mvi

import ru.otus.mvi.di.Injector

interface InjectorProvider {
    val injector: Injector
}