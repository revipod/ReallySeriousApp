package com.example.reallyseriousapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.reallyseriousapp.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : BaseActivity()  {

    @Inject
    lateinit var countryInfoViewModel : CountryInfoViewModel

    @Inject
    lateinit var eventBus: EventBus

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = countryInfoViewModel
        addLifeCycleObserver(countryInfoViewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        countryInfoViewModel.clearDisposable()
    }

    override fun getDisposable() = CompositeDisposable().apply {
        add(eventBus.startActivity(countryInfoViewModel).subscribe { startActivity(it) })
    }
}
