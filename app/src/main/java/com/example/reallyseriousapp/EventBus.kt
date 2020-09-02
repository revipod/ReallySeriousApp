package com.example.reallyseriousapp

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class EventBus @Inject constructor() {
    private val activityStartSubject = PublishSubject.create<ActivityStartEvent>()

    fun send(activityStartEvent: ActivityStartEvent) = activityStartSubject.onNext(activityStartEvent)

    fun startActivity(expectedClass: BaseViewModel) : Observable<ActivityStartEvent> =
        activityStartSubject.filter { expectedClass.javaClass == it.emitter.javaClass }
}

data class ActivityStartEvent(val emitter: BaseViewModel, val destination: KClass<*>)