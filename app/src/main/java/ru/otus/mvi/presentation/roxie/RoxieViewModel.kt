package ru.otus.mvi.presentation.roxie

import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.otus.mvi.domain.CharactersRepository
import ru.otus.mvi.presentation.roxie.mvi.Action
import ru.otus.mvi.presentation.roxie.mvi.Change
import ru.otus.mvi.presentation.roxie.mvi.State

class RoxieViewModel(
    private val repository: CharactersRepository,
) : BaseViewModel<Action, State>() {

    override val initialState = State()

}