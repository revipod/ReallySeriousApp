package com.example.reallyseriousapp

import androidx.databinding.ObservableField
import com.example.reallyseriousapp.retrofit.CountryByNameResponse
import com.example.reallyseriousapp.retrofit.CountryService
import com.example.reallyseriousapp.retrofit.ReactiveCountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UserInfoViewModel @Inject constructor (private val countryService: CountryService, private val reactiveCountryService: ReactiveCountryService) {

    val observableName = ObservableField<String>("observableName")
    val compositeDisposable = CompositeDisposable()

    fun getCountryByName(countryName: String) {
        countryService.getCountryByName(countryName).enqueue(object : Callback<List<CountryByNameResponse>>{
            override fun onFailure(call: Call<List<CountryByNameResponse>>, t: Throwable) {
                observableName.set("error")
            }

            override fun onResponse(
                call: Call<List<CountryByNameResponse>>,
                response: Response<List<CountryByNameResponse>>
            ) {
                observableName.set(response.body()!!.first().capital)
            }

        } )

    }

    fun getObservableCountryByName(countryName : String) {
        reactiveCountryService.getCountryByName(countryName)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                observableName.set(it.first().capital)
            },{
                observableName.set("error")
            })
    }

    fun kotlinObservableCountryByName(countryName : String) {
        reactiveCountryService.getCountryByName(countryName)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { observableName.set(it.first().capital) },
            onError = { observableName.set("error") })
    }

    fun getDisposableObsevableCountryByName(countryName : String) {
        compositeDisposable.add(reactiveCountryService.getCountryByName(countryName)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                observableName.set(it.first().capital)
            },{
                observableName.set("error")
            }))
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}