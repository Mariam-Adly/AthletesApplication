package com.example.athletesapplication.athleteDetails.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.athletesapplication.R
import com.example.athletesapplication.databinding.ActivityAthletesDetailsBinding
import com.example.athletesapplication.model.Athlete
import com.google.gson.Gson

class AthletesDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityAthletesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAthletesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val athleteObj = intent?.getStringExtra("myObject")
        if (athleteObj != null) {
            var athlete: Athlete = Gson().fromJson(athleteObj, Athlete::class.java)
            fillViews(athlete)

        }
    }

    fun fillViews(athlete: Athlete) {
        binding.tvName.text = athlete.name
        binding.tvDesc.text = athlete.brief
        Glide.with(this).load(athlete.image).placeholder(R.drawable.placeholder).into(binding.imgAthlete)

    }
}
