package com.example.reallyseriousapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.reallyseriousapp.databinding.ActivityMainBinding
import com.example.reallyseriousapp.databinding.ActivityMarvelBinding
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MarvelActivity : BaseActivity() {

    @Inject
    lateinit var eventBus: EventBus

    @Inject
    lateinit var marvelViewModel: MarvelViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMarvelBinding = DataBindingUtil.setContentView(this, R.layout.activity_marvel)
        binding.viewModel = marvelViewModel
        addLifeCycleObserver(marvelViewModel)
    }

    override fun getDisposable(): CompositeDisposable = CompositeDisposable().apply {
            add(eventBus.startActivity(marvelViewModel).subscribe { startActivity(it) })
        }
}
