package ru.otus.mvi.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.otus.mvi.domain.CharactersRepository
import ru.otus.mvi.domain.RaMCharacter

class MVVMViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<RaMCharacter>>(listOf())
    val items: StateFlow<List<RaMCharacter>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableSharedFlow<Boolean>(extraBufferCapacity = 1)
    val isError: SharedFlow<Boolean> = _isError.asSharedFlow()

    init {
        requestCharacters()
    }

    fun refresh() {
        requestCharacters()
    }

    private fun requestCharacters() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getAllCharacters()
                .onSuccess { characters ->
                    _isLoading.value = false
                    _items.value = characters
                }
                .onFailure {
                    _isLoading.value = false
                    _isError.tryEmit(true)
                }
        }
    }
}
