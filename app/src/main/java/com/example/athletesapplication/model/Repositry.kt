package com.example.athletesapplication.model

import androidx.lifecycle.viewModelScope
import com.example.athletesapplication.database.LocalSource
import com.example.athletesapplication.network.RemoteResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class Repositry private constructor(var remoteResource: RemoteResource , var localSource: LocalSource):RepoInterface{

    private val _product = MutableStateFlow<ResultData>(ResultData())
    var product = _product.asStateFlow()
    companion object {
        private var instance : Repositry? = null
        fun getInstance(remoteResource: RemoteResource, localSource: LocalSource):Repositry{
            return instance?: synchronized(this){
                val temp = Repositry(remoteResource , localSource)
                instance = temp
                temp
            }
        }
    }

    override suspend fun getStoredAthletes(): Flow<List<Athlete>> {
        return localSource.getStoredAthlete()
    }

    override suspend fun allAthletes(): Flow<Response<ResultData>> = flow {
        emit(remoteResource.getAthletesOverNetwork())
    }
    .flowOn(Dispatchers.IO)

    override suspend fun insertAthlete(athlete: List<Athlete>) {
       localSource.insertAthlete(athlete)
    }


}