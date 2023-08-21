package com.example.athletesapplication.database

import com.example.athletesapplication.model.Athlete
import kotlinx.coroutines.flow.Flow


interface LocalSource {

    suspend fun insertAthlete(athlete: List<Athlete>)
    suspend fun getStoredAthlete(): Flow<List<Athlete>>

}