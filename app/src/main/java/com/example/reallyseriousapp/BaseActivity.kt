package com.example.reallyseriousapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        compositeDisposable = getDisposable()
    }

    fun startActivity(activityStartEvent: ActivityStartEvent) = startActivity(Intent(this, activityStartEvent.destination.java))

    fun addLifeCycleObserver(viewModel: BaseViewModel) {
        lifecycle.addObserver(viewModel)
    }

    abstract fun getDisposable(): CompositeDisposable

    @Override
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}