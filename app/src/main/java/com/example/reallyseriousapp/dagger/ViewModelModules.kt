package com.example.reallyseriousapp.dagger

import com.example.reallyseriousapp.retrofit.CountryService
import com.example.reallyseriousapp.retrofit.ReactiveCountryService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ViewModelModules {

    @Provides
    fun providesRFCountryService(): CountryService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CountryService::class.java)
    }

    @Provides
    fun providesReactiveRFCountryService(): ReactiveCountryService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ReactiveCountryService::class.java)
    }


}