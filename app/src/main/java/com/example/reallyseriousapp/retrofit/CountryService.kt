package com.example.reallyseriousapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("/rest/v2/name/{name}")
    fun getCountryByName(@Path("name") countryName: String) : Call<List<CountryByNameResponse>>

}