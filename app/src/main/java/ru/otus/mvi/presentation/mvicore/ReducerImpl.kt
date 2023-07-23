package ru.otus.mvi.presentation.mvicore

import com.badoo.mvicore.element.Reducer
import ru.otus.mvi.presentation.mvicore.entities.Effect
import ru.otus.mvi.presentation.mvicore.entities.State

class ReducerImpl : Reducer<State, Effect> {

    override fun invoke(state: State, effect: Effect): State {
        return when (effect) {
            Effect.StartedLoading -> state.copy(
                isLoading = true
            )
            is Effect.ItemsLoaded -> state.copy(
                isLoading = false,
                items = effect.items
            )
            is Effect.ErrorLoading -> state.copy(
                isLoading = false
            )
        }
    }
}