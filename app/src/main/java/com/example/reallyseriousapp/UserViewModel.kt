package com.example.reallyseriousapp

import androidx.databinding.ObservableField

class UserViewModel {

    val name = "Mohsin"
    var observableName = ObservableField<String>("observableName")

}