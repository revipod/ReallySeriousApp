package com.example.reallyseriousapp

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setAdapter")
fun RecyclerView.setAdapter(countryListAdapter: CountryListAdapter) {
    adapter = countryListAdapter
}
