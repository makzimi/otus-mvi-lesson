package ru.otus.mvi

import androidx.fragment.app.Fragment
import ru.otus.mvi.di.Injector

interface InjectorProvider {
    val injector: Injector
}

fun Fragment.getInjector(): Injector {
    return (requireContext().applicationContext as InjectorProvider).injector
}