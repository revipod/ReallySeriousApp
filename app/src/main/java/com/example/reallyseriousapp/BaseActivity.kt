package com.example.reallyseriousapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner {
    fun startActivity(activityStartEvent: ActivityStartEvent) = startActivity(Intent(this, activityStartEvent.destination.java))
}