package com.example.athletesapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="athletes")
data class Athlete(
    @PrimaryKey val name: String,
    val image: String,
    val brief: String
    ) : Serializable