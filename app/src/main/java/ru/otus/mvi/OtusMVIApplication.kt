package ru.otus.mvi

import android.app.Application
import ru.otus.mvi.di.Injector

class OtusMVIApplication : Application(), InjectorProvider {

    private lateinit var _injector: Injector

    override val injector: Injector
        get() = _injector

    override fun onCreate() {
        super.onCreate()

        _injector = Injector(this)
    }
}
