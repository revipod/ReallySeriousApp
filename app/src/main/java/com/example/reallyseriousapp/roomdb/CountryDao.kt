package com.example.reallyseriousapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getAllCountries(): Single<List<Country>>

    @Insert
    fun insertNewCountry(country: Country) : Completable
}