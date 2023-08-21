package com.example.athletesapplication.network

import com.example.athletesapplication.model.ResultData
import retrofit2.Response

interface RemoteResource {

    suspend fun getAthletesOverNetwork(): Response<ResultData>

}