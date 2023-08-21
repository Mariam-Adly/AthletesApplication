package com.example.athletesapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.athletesapplication.model.Athlete

@Database(entities = arrayOf(Athlete::class), version = 1 )
abstract class AthletesDataBase : RoomDatabase() {
    abstract fun getAthleteDao(): AthletesDao
    companion object{
        @Volatile
        private var INSTANCE: AthletesDataBase? = null
        fun getInstance (ctx: Context): AthletesDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext, AthletesDataBase::class.java, "athletes_database")
                    .build()
                INSTANCE = instance
// return instance
                instance }
        }
    }
}
