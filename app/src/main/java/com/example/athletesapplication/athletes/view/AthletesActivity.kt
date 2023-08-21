package com.example.athletesapplication.athletes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.athletesapplication.athleteDetails.view.AthletesDetailsActivity
import com.example.athletesapplication.athletes.ApiState
import com.example.athletesapplication.athletes.viewmodel.AllAthletesViewModel
import com.example.athletesapplication.athletes.viewmodel.AllAthletesViewModelFactory
import com.example.athletesapplication.database.LocalSourceImpl
import com.example.athletesapplication.databinding.ActivityAthletesBinding
import com.example.athletesapplication.model.Athlete
import com.example.athletesapplication.model.Repositry
import com.example.athletesapplication.network.AthletesClient
import com.example.athletesapplication.utility.CheckNetwork
import com.google.gson.Gson

class AthletesActivity : AppCompatActivity() {

    lateinit var athletesViewModel: AllAthletesViewModel
    lateinit var athletesViewModelFactory: AllAthletesViewModelFactory
    lateinit var athletesAdapter: AthletesAdapter
    lateinit var binding: ActivityAthletesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAthletesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        athletesViewModelFactory = AllAthletesViewModelFactory(
            Repositry.getInstance(
                AthletesClient.getInstance(),LocalSourceImpl(this)))

        athletesViewModel = ViewModelProvider(this,athletesViewModelFactory).get(AllAthletesViewModel::class.java)

        athletesViewModel.getAthletes()

        athletesAdapter = AthletesAdapter {
            val gson = Gson().toJson(it)
            val intent = Intent(this@AthletesActivity, AthletesDetailsActivity::class.java)
            intent.putExtra("myObject",gson)
            startActivity(intent)
        }

        binding.rv.apply {
            this.adapter = athletesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val isConnected = CheckNetwork.isNetworkConnected(this)

        if (isConnected) {
            lifecycleScope.launchWhenStarted {
                athletesViewModel.athlete.collect{
                    when(it){
                        is ApiState.Loading->{
                            binding.rv.isVisible=false
                            binding.progressCircular.isVisible = true
                        }
                        is ApiState.Failure -> {
                            binding.rv.isVisible = false
                            binding.progressCircular.isVisible = true
                            Log.d("main", "onCreate: ${it.msg}")
                        }
                        is ApiState.Success->{
                            binding.rv.isVisible = true
                            binding.progressCircular.isVisible = false
                            athletesViewModel.addAthlete(it.data.body()?.athletes)
                            athletesAdapter.submitList(it.data.body()?.athletes)
                            athletesAdapter.notifyDataSetChanged()
                        }
                        is ApiState.Empty->{

                        }
                        is ApiState.SuccessDB->{

                        }
                    }
                }
            }
        } else {
            lifecycleScope.launchWhenStarted {
                athletesViewModel.getStoreAthletes()
                athletesViewModel.athlete.collect{
                    when(it){
                        is ApiState.Loading->{
                            binding.rv.isVisible=false
                            binding.progressCircular.isVisible = true

                        }
                        is ApiState.Failure -> {
                            binding.rv.isVisible = false
                            binding.progressCircular.isVisible = true
                            Log.d("main", "onCreate: ${it.msg}")
                        }
                        is ApiState.Success->{
                            binding.rv.isVisible=false
                            binding.progressCircular.isVisible = true
                        }
                        is ApiState.Empty->{

                        }
                        is ApiState.SuccessDB->{
                            binding.rv.isVisible = true
                            binding.progressCircular.isVisible = false
                            athletesAdapter.submitList(it.data)
                            athletesAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }

    }
}