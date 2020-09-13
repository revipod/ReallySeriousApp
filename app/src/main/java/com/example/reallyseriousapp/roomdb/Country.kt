package com.example.reallyseriousapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey val countryName : String,
    @ColumnInfo(name = "country_capital") val countryCapital: String?,
    @ColumnInfo(name = "population") val countryPopulation: Int
)