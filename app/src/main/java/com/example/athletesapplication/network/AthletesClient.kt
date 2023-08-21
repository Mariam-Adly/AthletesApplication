package com.example.athletesapplication.network

import com.example.athletesapplication.model.ResultData
import retrofit2.Response

class AthletesClient private constructor():RemoteResource {

    val apiService : ApiService by lazy {
        RetrofitHelper.getInstance().create(ApiService::class.java)
    }

    companion object {
        private var instance : AthletesClient? = null
        fun getInstance(): AthletesClient {
            return instance?: synchronized(this){
                val temp = AthletesClient()
                instance = temp
                temp
            }
        }
    }

    override suspend fun getAthletesOverNetwork(): Response<ResultData> {
        return apiService.allAthletes()
        println("jessy${apiService.allAthletes()}")
    }
}