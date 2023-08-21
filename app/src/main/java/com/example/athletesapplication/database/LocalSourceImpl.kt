package com.example.athletesapplication.database

import android.content.Context
import com.example.athletesapplication.model.Athlete
import kotlinx.coroutines.flow.Flow

class LocalSourceImpl(context: Context) : LocalSource {

    private val athletesDao : AthletesDao by lazy {
        val db  = AthletesDataBase.getInstance(context)
        db.getAthleteDao()
    }

    override suspend fun insertAthlete(athlete: List<Athlete>) {
       athletesDao.insertAthlete(athlete)
    }

    override suspend fun getStoredAthlete(): Flow<List<Athlete>> {
        return athletesDao.getAll()
    }
}