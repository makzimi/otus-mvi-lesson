package ru.otus.mvi.presentation.roxie.finish

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

    private val reducer: Reducer<State, Change> = { state, change ->
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

    init {
        bindActions()
    }

    private fun bindActions() {
        val itemsLoadedChange = actions
            .filter { it is Action.FeatureStarted || it is Action.SwipedToRefresh }
            .switchMap {
                repository.getAllCharactersSingle()
                    .subscribeOn(Schedulers.io())
                    .toObservable()
                    .map<Change> { Change.ListLoaded(it) }
                    .defaultIfEmpty(Change.ListLoaded(emptyList()))
                    .onErrorReturn { Change.Error }
                    .startWith(Change.Loading)
            }

        val errorShownChange = actions.ofType(Action.ErrorShown::class.java)
            .map { Change.ClearError }

        val allChanges = Observable.merge(
            itemsLoadedChange,
            errorShownChange,
        )

        disposables.add(
            allChanges
                .scan(initialState, reducer)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(state::setValue) { println("Error $it") }
        )
    }
}