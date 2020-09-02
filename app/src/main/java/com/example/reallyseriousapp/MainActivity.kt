package com.example.reallyseriousapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.reallyseriousapp.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity()  {

    @Inject
    lateinit var countryInfoViewModel : CountryInfoViewModel

    @Inject
    lateinit var eventBus: EventBus

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = countryInfoViewModel
        lifecycle.addObserver(countryInfoViewModel)
        eventBus.startActivity(countryInfoViewModel).subscribe { startActivity(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        countryInfoViewModel.clearDisposable()
    }
}
