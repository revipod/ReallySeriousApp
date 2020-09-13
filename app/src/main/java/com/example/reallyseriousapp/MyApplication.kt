package com.example.reallyseriousapp

import android.app.Activity
import android.app.Application
import com.example.reallyseriousapp.dagger.ContextModule
import com.example.reallyseriousapp.dagger.DaggerMyApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Override
    override fun onCreate() {
        super.onCreate()
        DaggerMyApplicationComponent.builder().contextModule(ContextModule(this)).build().inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }
}