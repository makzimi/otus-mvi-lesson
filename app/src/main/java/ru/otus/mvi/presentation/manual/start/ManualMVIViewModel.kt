package ru.otus.mvi.presentation.manual.start

import androidx.lifecycle.ViewModel
import ru.otus.mvi.domain.CharactersRepository

class ManualMVIViewModel(
    private val repository: CharactersRepository,
) : ViewModel() {

}