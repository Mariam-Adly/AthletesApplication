package com.example.athletesapplication.athletes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.athletesapplication.athletes.ApiState
import com.example.athletesapplication.model.Athlete
import com.example.athletesapplication.model.RepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllAthletesViewModel (private val _repo : RepoInterface): ViewModel() {

    private val _athlete : MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val athlete : StateFlow<ApiState> = _athlete

    fun getAthletes() = viewModelScope.launch{
        _athlete.value = ApiState.Loading
        _repo.allAthletes()
            .catch {
                    e-> _athlete.value = ApiState.Failure(e)
            }
            .collect{
                    data ->
                _athlete.value = ApiState.Success(data)
            }
    }

    fun addAthlete(athlete: List<Athlete>?){
        viewModelScope.launch(Dispatchers.IO) {
            if (athlete != null) {
                _repo.insertAthlete(athlete)
            }
        }

    }

     fun getStoreAthletes() =
        viewModelScope.launch{
            _athlete.value = ApiState.Loading
            _repo.getStoredAthletes()
                .catch {
                        e-> _athlete.value = ApiState.Failure(e)
                }
                .collect{
                        data ->
                    _athlete.value = ApiState.SuccessDB(data)
                }
    }

}