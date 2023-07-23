package ru.otus.mvi.presentation.mvicore

import com.badoo.mvicore.android.AndroidBindings

class MVICoreFragmentBindings(
    view: MVICoreFragment,
    private val feature: Feature,
    private val newsListener: NewsListener
) : AndroidBindings<MVICoreFragment>(view) {

    override fun setup(view: MVICoreFragment) {
        binder.bind(feature to view)
        binder.bind(feature.news to newsListener)
    }
}