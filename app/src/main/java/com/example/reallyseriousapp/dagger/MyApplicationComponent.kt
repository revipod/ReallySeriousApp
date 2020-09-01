package com.example.reallyseriousapp.dagger

import com.example.reallyseriousapp.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, ViewModelModules::class])
interface MyApplicationComponent : AndroidInjector<MyApplication>