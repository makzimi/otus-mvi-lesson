package ru.otus.mvi.presentation.mvicore

import com.badoo.mvicore.element.NewsPublisher
import ru.otus.mvi.presentation.mvicore.entities.Effect
import ru.otus.mvi.presentation.mvicore.entities.News
import ru.otus.mvi.presentation.mvicore.entities.State
import ru.otus.mvi.presentation.mvicore.entities.Wish

class NewsPublisherImpl : NewsPublisher<Wish, Effect, State, News> {

    override fun invoke(
        action: Wish,
        effect: Effect,
        state: State
    ): News? {
        return when (effect) {
            is Effect.ErrorLoading -> News.ErrorExecutingRequest(effect.throwable)
            else -> null
        }
    }
}