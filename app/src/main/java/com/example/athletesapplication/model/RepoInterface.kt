package com.example.athletesapplication.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

    suspend fun getStoredAthletes(): Flow<List<Athlete>>
    suspend fun allAthletes(): Flow<Response<ResultData>>
    suspend fun insertAthlete(athlete: List<Athlete>)

}
