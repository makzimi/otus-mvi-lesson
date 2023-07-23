package ru.otus.mvi.presentation.mvicore

import com.badoo.mvicore.feature.ActorReducerFeature
import ru.otus.mvi.domain.CharactersRepository
import ru.otus.mvi.presentation.mvicore.entities.Effect
import ru.otus.mvi.presentation.mvicore.entities.News
import ru.otus.mvi.presentation.mvicore.entities.State
import ru.otus.mvi.presentation.mvicore.entities.Wish

class Feature(
    charactersRepository: CharactersRepository,
) : ActorReducerFeature<Wish, Effect, State, News>(
    initialState = State(),
    actor = ActorImpl(charactersRepository),
    reducer = ReducerImpl(),
    newsPublisher = NewsPublisherImpl()
)







































/*
class View: Consumer<ViewModel> {

    private val button: Button = ...

    // Specify the fields to observe and actions to execute
    private val watcher = modelWatcher<ViewModel> {
        watch(ViewModel::buttonText) {
            button.text = it
        }
        watch(ViewModel::buttonAction, diff = byRef()) {
            button.setOnClickListener { it() }
        }
    }

    override fun accept(model) {
        // Pass the model
        watcher.invoke(model)
    }
}*/
