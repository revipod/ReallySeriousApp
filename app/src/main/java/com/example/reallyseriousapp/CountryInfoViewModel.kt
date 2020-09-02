package com.example.reallyseriousapp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
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

class CountryInfoViewModel @Inject constructor(
    private val countryService: CountryService,
    private val reactiveCountryService: ReactiveCountryService,
    private val eventBus: EventBus,
    val countryAdapter: CountryListAdapter
) : BaseViewModel() {

    val compositeDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        getSingleTypeCountryByName("Pakistan")
        launchMarvelActivity()
    }

    fun launchMarvelActivity() {
        eventBus.send(ActivityStartEvent(this, MarvelActivity::class))
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
        compositeDisposable.add(reactiveCountryService.getCountryByName(countryName)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = { createAndSetCountryItemList(it) },
                onError = { it.printStackTrace() })
        )
    }

    private fun createAndSetCountryItemList(it: List<CountryByNameResponse>?) {
        var itemViewModelList : MutableList<CountryItemViewModel> = ArrayList()
        itemViewModelList.add(CountryItemViewModel(it!!.first().name,it!!.first().capital))
        itemViewModelList.add(CountryItemViewModel(it!!.first().name,it!!.first().capital))
        countryAdapter.setAdapterData(itemViewModelList)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}