package com.example.athletesapplication.network

import com.example.athletesapplication.model.ResultData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("Bassem-Samy/f227855df4d197d3737c304e9377c4d4/raw/ece2a30b16a77ee58091886bf6d3445946e10a23/")
    suspend fun allAthletes(): Response<ResultData>
}