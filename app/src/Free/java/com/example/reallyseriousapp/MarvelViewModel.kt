package com.example.reallyseriousapp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject

class MarvelViewModel @Inject constructor(eventBus: EventBus) : BaseMarvelViewModel(eventBus) {

    init {
        hideScreen.set(true)
    }

}