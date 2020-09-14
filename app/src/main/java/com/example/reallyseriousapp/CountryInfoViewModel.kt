package com.example.reallyseriousapp

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.reallyseriousapp.retrofit.CountryByNameResponse
import com.example.reallyseriousapp.retrofit.CountryService
import com.example.reallyseriousapp.retrofit.ReactiveCountryService
import com.example.reallyseriousapp.roomdb.AppDatabase
import com.example.reallyseriousapp.roomdb.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountryInfoViewModel @Inject constructor(
    private val countryService: CountryService,
    private val reactiveCountryService: ReactiveCountryService,
    private val eventBus: EventBus,
    private val appDatabase: AppDatabase,
    val countryAdapter: CountryListAdapter
) : BaseViewModel() {

    val searchCountry = ObservableField<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
    }

    @SuppressLint("CheckResult")
    fun getCountryData() {
        if(searchCountry.get()!!.isNotEmpty()){
           addDisposable(appDatabase.countryDao().getAllCountries()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe{ response -> doesCountryExistInDB(response, searchCountry.get()!!)})
        }
    }

    fun launchMarvelActivity() {
        eventBus.send(ActivityStartEvent(this, MarvelActivity::class))
    }

    private fun doesCountryExistInDB(res: List<Country>, searchCountry: String) {
        if(res.isEmpty()) {
            getSingleTypeCountryByName(searchCountry)
        }
        else {
            for (x in res.indices) {
                if (res[x].countryName.equals(searchCountry, ignoreCase = true)) {
                    createCountryItemViewModel(res[x].countryName, res[x].countryCapital!!)
                    break
                } else if (x == res.size - 1) {
                    getSingleTypeCountryByName(searchCountry)
                }
            }
        }
    }

    //Making a network call without using Reactive Kotlin
    fun getCountryByName(countryName: String) {
        countryService.getCountryByName(countryName)
            .enqueue(object : Callback<List<CountryByNameResponse>> {
                override fun onFailure(call: Call<List<CountryByNameResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<CountryByNameResponse>>,
                    response: Response<List<CountryByNameResponse>>
                ) {
                    createAndSetCountryItemList(response.body())
                }

            })
    }

    fun getSingleTypeCountryByName(countryName: String) {
        addDisposable(reactiveCountryService.getCountryByName(countryName)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { createAndSetCountryItemList(it) },
                onError = { it.printStackTrace() })
        )
    }

    @SuppressLint("CheckResult")
    private fun createAndSetCountryItemList(it: List<CountryByNameResponse>?) {
        val countryName = it!!.first().name
        val countryCapital = it.first().capital
        val population = it.first().population
        addDisposable( appDatabase.countryDao().insertNewCountry(Country(countryName,countryCapital,population))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe { createCountryItemViewModel(countryName, countryCapital) })
    }

    private fun createCountryItemViewModel(countryName: String, countryCapital: String) {
        var itemViewModelList: MutableList<CountryItemViewModel> = ArrayList()
        itemViewModelList.add(CountryItemViewModel(countryName, countryCapital))
        itemViewModelList.add(CountryItemViewModel(countryName, countryCapital))
        countryAdapter.setAdapterData(itemViewModelList)
    }
}