package com.example.athletesapplication.athletes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.athletesapplication.model.RepoInterface

class AllAthletesViewModelFactory (private val repo : RepoInterface) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(AllAthletesViewModel::class.java)){
            AllAthletesViewModel(repo) as T
        }else{
            throw IllegalArgumentException("view model class not found")
        }
    }
}