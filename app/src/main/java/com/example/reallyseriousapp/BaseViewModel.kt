package com.example.reallyseriousapp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : LifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clearDisposable() {
        compositeDisposable.clear()
    }

}