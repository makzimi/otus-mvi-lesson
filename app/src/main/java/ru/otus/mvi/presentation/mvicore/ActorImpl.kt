package ru.otus.mvi.presentation.mvicore

import com.badoo.mvicore.element.Actor
import ru.otus.mvi.presentation.mvicore.entities.Effect.ErrorLoading
import ru.otus.mvi.presentation.mvicore.entities.Effect.ItemsLoaded
import ru.otus.mvi.presentation.mvicore.entities.Effect.StartedLoading
import ru.otus.mvi.presentation.mvicore.entities.Wish.FeatureStarted
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.otus.mvi.domain.CharactersRepository
import ru.otus.mvi.domain.RaMCharacter
import ru.otus.mvi.presentation.mvicore.entities.Effect
import ru.otus.mvi.presentation.mvicore.entities.State
import ru.otus.mvi.presentation.mvicore.entities.Wish

class ActorImpl(
    private val charactersRepository: CharactersRepository,
) : Actor<State, Wish, Effect> {

    override fun invoke(state: State, wish: Wish): Observable<out Effect> {
        return when (wish) {
            FeatureStarted, Wish.SwipedToRefresh -> loadCharacters()
                .map { ItemsLoaded(it) as Effect }
                .startWith(just(StartedLoading))
                .onErrorReturn { ErrorLoading(it) }
        }
    }

    private fun loadCharacters(): Observable<List<RaMCharacter>> {
        return charactersRepository.getAllCharactersSingle()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
    }
}
