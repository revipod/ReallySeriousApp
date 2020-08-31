package com.example.reallyseriousapp.retrofit

import com.google.gson.annotations.SerializedName

data class CountryByNameResponse (

    @SerializedName("capital")
    val capital: String

)