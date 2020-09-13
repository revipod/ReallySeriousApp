package com.example.reallyseriousapp.retrofit

import com.google.gson.annotations.SerializedName

data class CountryByNameResponse (

    @SerializedName("name")
    val name: String,

    @SerializedName("capital")
    val capital: String,

    @SerializedName("population")
    val population: Int
)