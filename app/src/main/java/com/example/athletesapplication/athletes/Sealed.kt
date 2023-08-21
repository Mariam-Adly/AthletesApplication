package com.example.athletesapplication.athletes

import com.example.athletesapplication.model.Athlete
import com.example.athletesapplication.model.ResultData
import retrofit2.Response

sealed class ApiState {
    class Success(val data: Response<ResultData>):ApiState()
    class SuccessDB(val data : List<Athlete>) : ApiState()
    class Failure(val msg: Throwable): ApiState()
    object Loading : ApiState()
    object Empty :  ApiState()
}