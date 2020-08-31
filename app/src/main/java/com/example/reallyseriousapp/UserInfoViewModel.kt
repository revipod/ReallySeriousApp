package com.example.reallyseriousapp

import androidx.databinding.ObservableField
import com.example.reallyseriousapp.retrofit.CountryByNameResponse
import com.example.reallyseriousapp.retrofit.CountryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoViewModel (private val countryService: CountryService) {

    var observableName = ObservableField<String>("observableName")

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

}