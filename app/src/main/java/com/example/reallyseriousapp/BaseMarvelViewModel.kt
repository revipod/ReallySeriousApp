package com.example.reallyseriousapp

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject

abstract class BaseMarvelViewModel (private val eventBus: EventBus) : BaseViewModel() {

    val hideScreen : ObservableBoolean = ObservableBoolean(false)

}
