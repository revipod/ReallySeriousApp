package com.example.reallyseriousapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Named("ApplicationContext")
    internal fun getContext(): Context {
        return context
    }
}