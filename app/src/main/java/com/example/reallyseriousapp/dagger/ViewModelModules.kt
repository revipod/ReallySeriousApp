package com.example.reallyseriousapp.dagger

import com.example.reallyseriousapp.UserInfoViewModel
import com.example.reallyseriousapp.retrofit.CountryService
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ViewModelModules {

    @Provides
    fun providesUserViewModel(countryService : CountryService) : UserInfoViewModel {
        return UserInfoViewModel(countryService)
    }

    @Provides
    fun providesRFCountryService(): CountryService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.eu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CountryService::class.java)
    }

}