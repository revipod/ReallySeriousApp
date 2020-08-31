package com.example.reallyseriousapp.retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("/rest/v2/name/{name}")
    fun getCountryByName(@Path("name") countryName: String) : Call<List<CountryByNameResponse>>

}

interface ReactiveCountryService {

    @GET("/rest/v2/name/{name}")
    fun getCountryByName(@Path("name") countryName: String) : Single<List<CountryByNameResponse>>

}