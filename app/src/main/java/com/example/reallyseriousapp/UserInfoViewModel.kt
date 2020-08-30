package com.example.reallyseriousapp

import androidx.databinding.ObservableField

class UserInfoViewModel (countryName : String) {

    val name = countryName
    var observableName = ObservableField<String>("observableName")

}