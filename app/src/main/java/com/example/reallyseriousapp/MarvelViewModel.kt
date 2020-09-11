package com.example.reallyseriousapp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject

class MarvelViewModel @Inject constructor (private val eventBus: EventBus) : BaseViewModel() {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {

    }

    fun launchMainActivity() {
        eventBus.send(ActivityStartEvent(this, MainActivity::class))
    }
}
