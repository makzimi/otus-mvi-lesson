package ru.otus.mvi.presentation.manual.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.otus.mvi.domain.CharactersRepository

typealias Dispatcher = suspend (State, Action) -> Unit
typealias Reducer = (State, Change) -> State

class ManualMVIViewModel(
    private val repository: CharactersRepository,
) : ViewModel() {

    private val _actions = MutableSharedFlow<Action>(extraBufferCapacity = 1)
    private val _changes = MutableSharedFlow<Change>(
        extraBufferCapacity = 5,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    private val _state = MutableStateFlow<State>(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        bind()
    }

    fun sendAction(action: Action) {
        _actions.tryEmit(action)
    }

    private fun bind() {
        _actions.onEach { action -> dispatcher(state.value, action) }.launchIn(viewModelScope)
        _changes.onEach { change -> _state.value = reducer(state.value, change) }
            .launchIn(viewModelScope)
    }

    private val dispatcher: Dispatcher = { state, action ->
        when (action) {
            is Action.LoadCharacters -> {
                _changes.tryEmit(Change.Loading)
                repository.getAllCharacters()
                    .onSuccess { data ->
                        _changes.tryEmit(Change.ListLoaded(data))
                    }
                    .onFailure {
                        _changes.tryEmit(Change.Error)
                    }
            }

            Action.ErrorShown -> {
                _changes.tryEmit(Change.ClearError)
            }
        }
    }

    private val reducer: Reducer = { state, change ->
        when (change) {
            is Change.Loading -> state.copy(isLoading = true)
            is Change.ListLoaded -> state.copy(
                isLoading = false,
                items = change.items,
            )

            is Change.Error -> state.copy(
                isLoading = false,
                isError = true,
            )

            is Change.ClearError -> state.copy(isError = false)
        }
    }
}