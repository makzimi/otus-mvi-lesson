package ru.otus.mvi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.otus.mvi.domain.CharactersRepository
import ru.otus.mvi.presentation.mvvm.MVVMViewModel
import ru.otus.mvi.presentation.roxie.RoxieViewModel
import ru.otus.mvi.presentation.roxie.finish.RoxieViewModel as FinishRoxieVM

class CustomViewModelFactory(private val repository: CharactersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MVVMViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return MVVMViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RoxieViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return RoxieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FinishRoxieVM::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return FinishRoxieVM(repository) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
