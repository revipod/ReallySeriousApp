package com.example.reallyseriousapp.dagger

import com.example.reallyseriousapp.MainActivity
import com.example.reallyseriousapp.MarvelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivityInjection(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesMarvelActivityInjection(): MarvelActivity
}