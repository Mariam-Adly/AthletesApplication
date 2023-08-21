package com.example.athletesapplication.database

import androidx.room.*
import com.example.athletesapplication.model.Athlete
import kotlinx.coroutines.flow.Flow

@Dao
interface AthletesDao {
    @Query("Select * From athletes")
    fun getAll(): Flow<List<Athlete>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAthlete(athlete: List<Athlete>)
}